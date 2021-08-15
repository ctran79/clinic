import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {DictionaryValueService} from "../../service/dictionary-value.service";
import {Observable} from "rxjs";
import {debounceTime, distinctUntilChanged, map, startWith, switchMap} from "rxjs/operators";
import {DictionaryValue} from "../../domain/dictionary-value";
import {DictionaryValueSearchModel} from "../../domain/dictionary-value-search-model";
import {DictionaryCode} from "../../domain/dictionary-code";
import {MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";

@Component({
  selector: 'app-diagnosis-dialog',
  templateUrl: './diagnosis-dialog.component.html',
  styleUrls: ['./diagnosis-dialog.component.css']
})
export class DiagnosisDialogComponent implements OnInit {

  form!: FormGroup;
  filteredDiagnoses!: Observable<DictionaryValue[]>;
  selectedDiagnosis!: DictionaryValue;

  constructor(public dialogRef: MatDialogRef<DiagnosisDialogComponent>,
              public formBuilder: FormBuilder,
              public dictValueService: DictionaryValueService) {
  }

  ngOnInit(): void {
    this.createFormView();
    this.filteredDiagnoses = this.getControl('diagnosis').valueChanges
      .pipe(
        startWith(''),
        debounceTime(400),
        distinctUntilChanged(),
        switchMap(val => {
          return this.filter(val || '')
        })
      );
  }

  getDiagnosisName(diagnosis: DictionaryValue) {
    return diagnosis?.value;
  }

  filter(val: string): Observable<DictionaryValue[]> {
    let diagnosisSearchModel = new DictionaryValueSearchModel();
    diagnosisSearchModel.fields.forEach(field => {
      if (field.name === 'dictionary') {
        field.value = DictionaryCode.ICD10;
      } else if (field.name === 'value') {
        field.value = val;
      }
    });

    return this.dictValueService.search(diagnosisSearchModel)
      .pipe(
        map(response => response.content.filter(diagnoses => diagnoses.value.toLowerCase().indexOf(val.toLowerCase()) >= 0))
      );
  }

  getControl(name: string): FormControl {
    return this.form.controls[name] as FormControl;
  }

  selectDiagnosis($event: MatAutocompleteSelectedEvent) {
    this.selectedDiagnosis = $event.option.value;
  }

  saveDiagnosis() {
    if (this.form.valid) {
      this.dialogRef.close(this.selectedDiagnosis);
    }
  }

  private createFormView() {
    this.form = this.formBuilder.group({
      diagnosis: [null, Validators.required]
    });
  }
}
