import {Component} from '@angular/core';
import {TableBase} from "../../table-base";
import {Router} from "@angular/router";
import {PrescriptionService} from "../../service/prescription.service";
import {Prescription} from "../../domain/prescription";
import {PrescriptionSearchModel} from "../../domain/prescription-search-model";
import {BaseObject} from "../../domain/base-object";

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

  initSearchModel(): PrescriptionSearchModel {
    return new PrescriptionSearchModel();
  }

  async edit(obj: BaseObject) {
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
}
