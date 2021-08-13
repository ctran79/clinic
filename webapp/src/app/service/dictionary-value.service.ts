import { Injectable } from '@angular/core';
import {CrudService} from "./crud.service";
import {Dictionary} from "../domain/dictionary";
import {DictionaryValue} from "../domain/dictionary-value";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DictionaryValueService extends CrudService<DictionaryValue>{

  constructor(protected http: HttpClient) { super(http); }

  protected getEndpoint(): string {
    return "dictionary-value";
  }
}
