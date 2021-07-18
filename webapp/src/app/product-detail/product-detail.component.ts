import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Product} from "../domain/product";
import {ActivatedRoute, Route} from "@angular/router";
import {parse} from "@angular/compiler/src/render3/view/style_parser";

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
  public activatedRoute: ActivatedRoute,
) {
  }

  ngOnInit(): void {
    this.createFormView();
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
    }
  }

  getControl(name: string) : AbstractControl {
    return this.form.controls[name];
  }

  createObject() {
    this.product = {
      id: this.id,
      name: this.getControl('name').value,
      code:  this.getControl('code').value,
      note:  this.getControl('note').value,
    };
    return this.product;
  }
}
