import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Product} from "../domain/product";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../service/product.service";
import {Observable} from "rxjs";
import {debounceTime, distinctUntilChanged, map, startWith, switchMap} from "rxjs/operators";
import {ProductSearchModel} from "../domain/product-search-model";
import {MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  form!: FormGroup;
  product!: Product;
  id: number = this.activatedRoute.snapshot.params.id;
  filteredProducts!: Observable<Product[]>

  constructor(public formBuilder: FormBuilder,
              public router: Router,
              public activatedRoute: ActivatedRoute,
              public productService: ProductService) {
  }

  ngOnInit(): void {
    this.createFormView();
    this.getData();
    this.filteredProducts = this.getControl('name').valueChanges
      .pipe(
        startWith(''),
        debounceTime(400),
        distinctUntilChanged(),
        switchMap(val => {
          return this.filter(val || '')
        })
      );
  }

  filter(val: string): Observable<Product[]> {
    let productSearchModel = new ProductSearchModel();
    productSearchModel.fields.forEach(field => {
      if (field.name = 'name') {
        field.value = val;
      }
    });

    return this.productService.search(productSearchModel)
      .pipe(
        map(response => response.content.filter(product => product.name.toLowerCase().indexOf(val.toLowerCase()) >= 0))
      );
  }


  createFormView() {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      code: [''],
      note: ['']
    });
  }

  errorHandling(control: string, error: string) {
    return this.getControl(control).hasError(error);
  }

  onSubmit() {
    if (this.form.valid) {
      const product = this.createObject();
      this.productService.saveModel(product).subscribe(product => this.navigateToList());
    }
  }

  getControl(name: string): FormControl {
    return this.form.controls[name] as FormControl;
  }

  createObject() {
    this.product = {
      id: this.id,
      name: this.getControl('name').value,
      code: this.getControl('code').value,
      note: this.getControl('note').value,
    };
    return this.product;
  }

  getData() {
    if (this.id) {
      this.productService.getModel(this.id).subscribe(product => this.patchFormValue(product));
    }
  }

  patchFormValue(product: Product) {
    this.product = product;
    this.form.patchValue({
      name: product.name,
      code: product.code,
      note: product.note
    });
  }

  async navigateToList() {
    await this.router.navigate(['/product-list']);
  }
}
