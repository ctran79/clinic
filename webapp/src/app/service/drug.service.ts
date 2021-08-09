import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {Drug} from "../domain/drug";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DrugService extends CrudService<Drug> {

  constructor(protected http: HttpClient) {
    super(http);
  }

  protected getEndpoint(): string {
    return 'drug';
  }
}
