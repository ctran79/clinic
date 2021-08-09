import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Indication} from "../domain/indication";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {debounceTime, distinctUntilChanged, map, startWith, switchMap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Drug} from "../domain/drug";
import {DrugSearchModel} from "../domain/drug-search-model";
import {DrugService} from "../service/drug.service";
import {MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";

@Component({
  selector: 'app-indication-dialog',
  templateUrl: './indication-dialog.component.html',
  styleUrls: ['./indication-dialog.component.css']
})
export class IndicationDialogComponent implements OnInit {

  form!: FormGroup;
  filteredProducts!: Observable<Drug[]>;
  selectedProduct!: Drug;

  constructor(public dialogRef: MatDialogRef<IndicationDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Indication,
              public formBuilder: FormBuilder,
              public productService: DrugService) {
  }

  ngOnInit(): void {
    this.createFormView();
    if (this.data) {
      this.patchForm(this.data);
    }
    this.filteredProducts = this.getControl('productName').valueChanges
      .pipe(
        startWith(this.data ? this.data.seqNo : ''),
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

  filter(val: string): Observable<Drug[]> {
    let productSearchModel = new DrugSearchModel();
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

  patchForm(data: Indication) {
    this.form.patchValue({
    })
  }

  selectProduct($event: MatAutocompleteSelectedEvent) {
    this.selectedProduct = $event.option.value;
    this.form.patchValue({
    })
  }

  getProductName(product: Drug) {
    return product ? product.name : '';
  };

  closeDialog() {
    if (this.form.valid) {
    }
  }
}
