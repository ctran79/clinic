import {Pipe, PipeTransform} from '@angular/core';
import {Patient} from "../domain/patient";

@Pipe({
  name: 'patientAge'
})
export class PatientAgePipe implements PipeTransform {

  transform(patient: Patient, ...args: unknown[]): string {
    const birthday = new Date(patient.birthday);
    const examDate = new Date(patient.createDate!);
    let months = (examDate.getFullYear() - birthday.getFullYear()) * 12;

    months += examDate.getMonth() - birthday.getMonth();
    if (months < 0) {
      months = 0;
    }

    return months <= 72 ? months + ' tháng' : Math.ceil((months - 1) / 12 + 1) + ' tuổi';
  }
}
