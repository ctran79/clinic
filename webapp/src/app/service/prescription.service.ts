import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {HttpClient} from "@angular/common/http";
import {Prescription} from "../domain/prescription";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService extends CrudService<Prescription> {

  constructor(protected http: HttpClient) {
    super(http);
  }

  getByPatientId(patientId: number): Observable<Prescription> {
    return this.http.get<Prescription>(`${this.api}/patient/${patientId}`);
  }

  protected getEndpoint(): string {
    return 'prescription';
  }
}
