import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BaseObject} from "../domain/base-object";

@Injectable({
  providedIn: 'root'
})
export abstract class CrudService<T extends BaseObject> {

  endpoint: string;
  server: string = 'localhost:8080';

  protected constructor(protected http: HttpClient) {
    this.endpoint = `${this.server}/${this.getEndpoint()}`;
  }

  public saveModel(model: T): Observable<T> {
    if (model.id) {
      return this.http.post<T>(`${this.endpoint}/${model.id}`, model);
    } else {
      return this.http.post<T>(`this.endpoint`, model);
    }
  }

  protected abstract getEndpoint(): string;
}
