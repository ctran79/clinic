import {Component} from '@angular/core';
import {TableBase} from "../table-base";
import {PatientSearchModel} from "../domain/patient-search-model";
import {BaseSearchModel} from "../domain/base-search-model";
import {PatientService} from "../service/patient.service";
import {Patient} from "../domain/patient";
import {Router} from "@angular/router";
import {BaseObject} from "../domain/base-object";
import {PrescriptionService} from "../service/prescription.service";

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent extends TableBase<Patient> {

  displayedColumns: string[] = ['createDate', 'isExamined', 'age', 'name', 'address', 'actions'];

  constructor(public router: Router,
              public patientService: PatientService,
              public prescriptionService: PrescriptionService) {
    super(patientService);
  }

  initSearchModel(): BaseSearchModel {
    return new PatientSearchModel();
  }

  patientIsExamined(patient: Patient): string {
    return patient.isExamined ? 'Đã khám' : 'Chưa khám';
  }

  async edit(obj: BaseObject): Promise<void> {
    await this.router.navigate([`/patient-detail/${obj.id}`]);
  }

  async add(): Promise<void> {
    await this.router.navigate(['/patient-detail']);
  }

  async addOrEditPrescription(patient: Patient) {
    this.prescriptionService.getByPatient(patient.id!).subscribe(prescription => {
      if (prescription) {
        this.router.navigate([`/prescription-detail/${prescription.id}`]);
      } else {
        this.router.navigate([`/prescription-detail`], {
          queryParams: {
            patientId: patient.id
          }
        });
      }
    });
  }
}
