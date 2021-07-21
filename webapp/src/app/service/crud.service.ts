import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {BaseObject} from "../domain/base-object";
import {environment} from "../../environments/environment";
import {BaseSearchModel} from "../domain/base-search-model";

@Injectable({
  providedIn: 'root'
})
export abstract class CrudService<T extends BaseObject> {

  api: string;
  server: string = environment.server;

  protected constructor(protected http: HttpClient) {
    this.api = `${this.server}/${this.getEndpoint()}`;
  }

  public saveModel(model: T): Observable<T> {
       return this.http.put<T>(`${this.api}/${model.id}`, model);
   }

  public search(searchModel: BaseSearchModel) {
  }

  public getModel(id: number): Observable<T> {
    return this.http.get<T>(`${this.api}/${id}`);
  }

  protected abstract getEndpoint(): string;
}
