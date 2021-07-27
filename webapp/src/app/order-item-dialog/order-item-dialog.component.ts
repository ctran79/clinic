import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {OrderItem} from "../domain/order-item";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {debounceTime, distinctUntilChanged, map, startWith, switchMap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Product} from "../domain/product";
import {ProductSearchModel} from "../domain/product-search-model";
import {ProductService} from "../service/product.service";
import {MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";

@Component({
  selector: 'app-order-item-dialog',
  templateUrl: './order-item-dialog.component.html',
  styleUrls: ['./order-item-dialog.component.css']
})
export class OrderItemDialogComponent implements OnInit {

  form!: FormGroup;
  filteredProducts!: Observable<Product[]>;
  selectedProduct!: Product;

  constructor(public dialogRef: MatDialogRef<OrderItemDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: OrderItem,
              public formBuilder: FormBuilder,
              public productService: ProductService) {
  }

  ngOnInit(): void {
    this.createFormView();
    if (this.data) {
      this.patchForm(this.data);
    }
    this.filteredProducts = this.getControl('productName').valueChanges
      .pipe(
        startWith(this.data ? this.data.productName : ''),
        debounceTime(400),
        distinctUntilChanged(),
        switchMap(val => {
          return this.filter(val || '')
        })
      );
  }

  createFormView() {
    this.form = this.formBuilder.group({
      productId: [null, Validators.required],
      productName: ['', Validators.required],
      note: [],
    })
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

  getControl(name: string): FormControl {
    return this.form.controls[name] as FormControl;
  }

  errorHandling(control: string, error: string) {
    return this.getControl(control).hasError(error);
  }

  patchForm(data: OrderItem) {
    this.form.patchValue({
      productId: data.productId,
      productName: data.productName,
      note: data.note,
    })
  }

  selectProduct($event: MatAutocompleteSelectedEvent) {
    this.selectedProduct = $event.option.value;
    this.form.patchValue({
      productId: this.selectedProduct.id,
      productName: this.selectedProduct.name,
      note: this.selectedProduct.note
    })
  }

  getProductName(product: Product) {
    return product ? product.name : '';
  };

  createOrderItem(): OrderItem {
    return {
      seqNo: this.data ? this.data.seqNo : -1,
      productId: this.getControl('productId').value,
      productName: this.getControl('productName').value,
      note: this.getControl('note').value
    }
  }

  closeDialog() {
    if (this.form.valid) {
      const orderItem = this.createOrderItem();
      this.dialogRef.close(orderItem);
    }
  }
}
