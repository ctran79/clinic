import { Pipe, PipeTransform } from '@angular/core';
import {Patient} from "../domain/patient";

@Pipe({
  name: 'patientAge'
})
export class PatientAgePipe implements PipeTransform {

  transform(patient: Patient, ...args: unknown[]): string {
    const birthday = new Date(patient.birthday);
    const examDate = patient.createDate!;
    const diffTime = Math.abs(examDate.getTime() - birthday.getTime());
    const diffMonths = Math.ceil(diffTime / (1000 * 60 * 60 * 24 * 30));

    return diffMonths <= 72 ? diffMonths + ' tháng' : Math.ceil((diffMonths - 1) / 12 + 1) + ' tuổi';
  }
}
