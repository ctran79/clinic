import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PatientService} from "../../service/patient.service";
import {Patient} from "../../domain/patient";
import {DictionaryValue} from "../../domain/dictionary-value";
import {DictionaryService} from "../../service/dictionary.service";
import {DictionaryCode} from "../../domain/dictionary-code";
import {compareFn} from "../../utils/utils";

@Component({
  selector: 'app-patient-detail',
  templateUrl: './patient-detail.component.html',
  styleUrls: ['./patient-detail.component.css']
})
export class PatientDetailComponent implements OnInit {

  form!: FormGroup;
  patient!: Patient;
  id: number = this.activatedRoute.snapshot.params.id;
  genderDict: DictionaryValue[] = [];
  compareFn =  compareFn;

  constructor(public formBuilder: FormBuilder,
              public router: Router,
              public dictionaryService: DictionaryService,
              public activatedRoute: ActivatedRoute,
              public patientService: PatientService) {
  }

  ngOnInit(): void {
    this.createFormView();
    this.getDictionary();
    this.getData();
  }

  createFormView() {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      birthday: ['', Validators.required],
      gender: ['', Validators.required],
      telephone: [''],
      address: [''],
      weight: [0],
      height: [0],
      isExamined: [false]
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

  createObject(): Patient {
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
    return this.patient;
  }

  getData() {
    if (this.id) {
      this.patientService.getModel(this.id).subscribe(patient => this.patchFormValue(patient));
    }
  }

  patchFormValue(patient: Patient) {
    this.patient = patient;
    this.form.patchValue(patient);
  }

  async navigateToList() {
    await this.router.navigate(['/patient-list']);
  }

  private getDictionary() {
    this.dictionaryService.getDictByCode(DictionaryCode.GENDER).subscribe(dictionary => {
      this.genderDict = dictionary.dictionaryValues;
    });
  }
}
