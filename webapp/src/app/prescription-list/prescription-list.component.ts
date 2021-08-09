import {Component} from '@angular/core';
import {TableBase} from "../table-base";
import {Router} from "@angular/router";
import {PrescriptionService} from "../service/prescription.service";
import {Prescription} from "../domain/prescription";
import {PrescriptionSearchModel} from "../domain/prescription-search-model";

@Component({
  selector: 'app-prescription-list',
  templateUrl: './prescription-list.component.html',
  styleUrls: ['./prescription-list.component.css']
})
export class PrescriptionListComponent extends TableBase<Prescription> {
  displayedColumns: string[] = ['createDate', 'client', 'age', 'address', 'actions'];

  constructor(public router: Router,
              public orderService: PrescriptionService) {
    super(orderService);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  initSearchModel(): PrescriptionSearchModel {
    return new PrescriptionSearchModel();
  }

  async edit(obj: Prescription) {
    await this.router.navigate([`/prescription-detail/${obj.id}`]);
  }

  async add() {
    await this.router.navigate(['/prescription-detail']);
  }

  getPatientName(prescription: Prescription): string {
    return prescription.patient.name;
  }

  getPatientAddress(prescription: Prescription): string {
    return prescription.patient.address;
  }

  getPatientAge(prescription: Prescription): string {
    const birthday = new Date(prescription.patient.birthday);
    const examDate = new Date(prescription.patient.createDate);
    const diffTime = Math.abs(examDate.getTime() - birthday.getTime());
    const diffMonths = Math.ceil(diffTime / (1000 * 60 * 60 * 24 * 30));

    return diffMonths <= 72 ? diffMonths + ' tháng' : Math.ceil((diffMonths - 1) / 12 + 1) + ' tuổi';
  }
}
