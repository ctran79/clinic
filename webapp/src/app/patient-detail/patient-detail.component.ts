import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Drug} from "../domain/drug";
import {Observable} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {DrugService} from "../service/drug.service";
import {debounceTime, distinctUntilChanged, map, startWith, switchMap} from "rxjs/operators";
import {DrugSearchModel} from "../domain/drug-search-model";
import {PatientService} from "../service/patient.service";
import {Patient} from "../domain/patient";
import {ListItem} from "../domain/list-item";

@Component({
  selector: 'app-patient-detail',
  templateUrl: './patient-detail.component.html',
  styleUrls: ['./patient-detail.component.css']
})
export class PatientDetailComponent implements OnInit {

  form!: FormGroup;
  patient!: Patient;
  id: number = this.activatedRoute.snapshot.params.id;
  genderDict: ListItem[] = [];

  constructor(public formBuilder: FormBuilder,
              public router: Router,
              public activatedRoute: ActivatedRoute,
              public patientService: PatientService) {
  }

  ngOnInit(): void {
    this.createFormView();
    this.getData();
  }

  createFormView() {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      birthday: ['', Validators.required],
      gender: [''],
      telephone: [''],
      address: [''],
      weight: [0],
      height: [0],
      isExamined: []
    });
  }

  errorHandling(control: string, error: string) {
    return this.getControl(control).hasError(error);
  }

  onSubmit() {
    if (this.form.valid) {
      const patient = this.createObject();
      this.patientService.saveModel(patient).subscribe(patient => this.navigateToList());
    }
  }

  getControl(name: string): FormControl {
    return this.form.controls[name] as FormControl;
  }

  createObject() : Patient {
    this.patient = {
      id: this.id,
      name: this.getControl('name').value,
      birthday: this.getControl('birthday').value,
      gender: this.getControl('gender').value,
      telephone: this.getControl('telephone').value,
      address: this.getControl('address').value,
      weight: this.getControl('weight').value,
      height: this.getControl('height').value,
      isExamined: this.getControl('isExamined').value,
    };
    return this.patient ;
  }

  getData() {
    if (this.id) {
      this.patientService.getModel(this.id).subscribe(patient => this.patchFormValue(patient));
    }
  }

  patchFormValue(drug: Patient) {
    this.patient = drug;
    this.form.patchValue({
    });
  }

  async navigateToList() {
    await this.router.navigate(['/patient-list']);
  }
}
