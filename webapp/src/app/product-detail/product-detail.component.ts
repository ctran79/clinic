import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Product} from "../domain/product";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {ProductService} from "../service/product.service";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  form!: FormGroup;
  product!: Product;
  id: number = this.activatedRoute.snapshot.params.id;

  constructor(public formBuilder: FormBuilder,
              public router: Router,
              public activatedRoute: ActivatedRoute,
              public productService: ProductService) {
  }

  ngOnInit(): void {
    this.createFormView();
    this.getData();
  }

  createFormView() {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      code: [''],
      note: ['']
    });
  }

  errorHandling(control: string, error: string) {
    return this.form.controls[control].hasError(error);
  }

  onSubmit() {
    if (this.form.valid) {
      const product = this.createObject();
      this.productService.saveModel(product).subscribe(product => this.navigateToList());
    }
  }

  getControl(name: string): AbstractControl {
    return this.form.controls[name];
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

   navigateToList() {
    this.router.navigate(['/product-list']);
  }
}
