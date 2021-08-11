import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {Drug} from "../domain/drug";
import {Dictionary} from "../domain/dictionary";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DictService extends CrudService<Dictionary> {

  constructor(protected http: HttpClient) {
    super(http);
  }

  protected getEndpoint(): string {
    return "dictionary";
  }


}
