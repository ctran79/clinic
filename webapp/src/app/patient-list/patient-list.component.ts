import {Component} from '@angular/core';
import {TableBase} from "../table-base";
import {Prescription} from "../domain/prescription";
import {PatientSearchModel} from "../domain/patient-search-model";
import {BaseSearchModel} from "../domain/base-search-model";
import {PatientService} from "../service/patient.service";
import {Patient} from "../domain/patient";

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent extends TableBase<Patient> {

  constructor(public patientService: PatientService) {
    super(patientService);
  }

  ngOnInit(): void {
  }

  initSearchModel(): BaseSearchModel {
    return new PatientSearchModel();
  }

  patientIsExamined(patient: Patient){
    return patient.isExamined ? 'Đã khám': 'Chưa khám';
  }

  getPatientAge(patient: Patient) {
    const birthday = new Date(patient.birthday);
    const examDate = new Date(patient.createDate);
    const diffTime = Math.abs(examDate.getTime() - birthday.getTime());
    const diffMonths = Math.ceil(diffTime / (1000 * 60 * 60 * 24 * 30));

    return diffMonths <= 72 ? diffMonths + ' tháng' : Math.ceil((diffMonths - 1) / 12 + 1) + ' tuổi';
  }
}
