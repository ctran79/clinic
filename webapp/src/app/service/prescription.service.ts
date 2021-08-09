import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {HttpClient} from "@angular/common/http";
import {Prescription} from "../domain/prescription";

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService extends CrudService<Prescription> {

  constructor(protected http: HttpClient) {
    super(http);
  }

  protected getEndpoint(): string {
    return 'prescription';
  }
}
