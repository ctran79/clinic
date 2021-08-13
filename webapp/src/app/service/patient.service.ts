import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {Patient} from "../domain/patient";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Prescription} from "../domain/prescription";

@Injectable({
  providedIn: 'root'
})
export class PatientService extends CrudService<Patient> {

  constructor(protected http: HttpClient) {
    super(http);
  }

  protected getEndpoint(): string {
    return "patient";
  }
}
