import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {Dictionary} from "../domain/dictionary";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DictService extends CrudService<Dictionary> {

  constructor(protected http: HttpClient) {
    super(http);
  }

  public getDictByCode(code: string): Observable<Dictionary> {
    return this.http.get<Dictionary>(`${this.api}/code/${code}`);
  }

  public getDictionaryByCode(code: string): Observable<Dictionary> {
    return this.http.get<Dictionary>(`${this.api}/code/${code}`);
  }

  protected getEndpoint(): string {
    return "dictionary";
  }
}
