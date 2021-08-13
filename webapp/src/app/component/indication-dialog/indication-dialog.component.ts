import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Indication} from "../../domain/indication";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {debounceTime, distinctUntilChanged, map, startWith, switchMap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Drug} from "../../domain/drug";
import {DrugSearchModel} from "../../domain/drug-search-model";
import {DrugService} from "../../service/drug.service";
import {MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";

@Component({
  selector: 'app-indication-dialog',
  templateUrl: './indication-dialog.component.html',
  styleUrls: ['./indication-dialog.component.css']
})
export class IndicationDialogComponent implements OnInit {

  form!: FormGroup;
  filteredDrugs!: Observable<Drug[]>;
  selectedDrug!: Drug;

  constructor(public dialogRef: MatDialogRef<IndicationDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Indication,
              public formBuilder: FormBuilder,
              public drugService: DrugService) {
  }

  ngOnInit(): void {
    this.createFormView();
    if (this.data) {
      this.patchForm(this.data);
    }
    this.filteredDrugs = this.getControl('drug').valueChanges
      .pipe(
        startWith(this.data ? this.data.drug.name : ''),
        debounceTime(400),
        distinctUntilChanged(),
        switchMap(val => {
          return this.filter(val || '')
        })
      );
  }

  createFormView() {
    this.form = this.formBuilder.group({
      drug: ['', Validators.required],
      quantity: [1, Validators.required],
      usage: ['', Validators.required],
    })
  }

  filter(val: string): Observable<Drug[]> {
    let drugSearchModel = new DrugSearchModel();
    drugSearchModel.fields.forEach(field => {
      if (field.name = 'name') {
        field.value = val;
      }
    });

    return this.drugService.search(drugSearchModel)
      .pipe(
        map(response => response.content.filter(drug => drug.name.toLowerCase().indexOf(val.toLowerCase()) >= 0))
      );
  }

  getControl(name: string): FormControl {
    return this.form.controls[name] as FormControl;
  }

  patchForm(data: Indication) {
    this.form.patchValue({})
  }

  selectProduct($event: MatAutocompleteSelectedEvent) {
    this.selectedDrug = $event.option.value;
    this.form.patchValue({
      usage: this.selectedDrug.usage
    });
  }

  saveIndication() {
    if (this.form.valid) {
      const indication = this.createIndication();
      this.dialogRef.close(indication);
    }
  }

  private createIndication() : Indication {
    return this.form.value;
  }
}
