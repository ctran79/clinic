import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {BaseObject} from "../domain/base-object";
import {environment} from "../../environments/environment";
import {BaseSearchModel} from "../domain/base-search-model";
import * as _ from "lodash";
import {PagedSearchResult} from "../domain/paged-search-result";
import {formatDate} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export abstract class CrudService<T extends BaseObject> {

  readonly headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  api: string;
  server: string = environment.server;

  protected constructor(protected http: HttpClient) {
    this.api = `${this.server}/${this.getEndpoint()}`;
  }

  public saveModel(model: T): Observable<T> {
    return this.http.post<T>(`${this.api}`, model, {headers: this.headers});
  }

  public search(searchModel: BaseSearchModel): Observable<PagedSearchResult<T>> {
    const urlParams = this.buildSearchParams(searchModel);
    return this.http.get<PagedSearchResult<T>>(`${this.api}/search`, {
      headers: this.headers,
      params: urlParams
    });
  }

  public getModel(id: number): Observable<T> {
    return this.http.get<T>(`${this.api}/${id}`);
  }

  public getAllModel() : Observable<T[]> {
    return this.http.get<T[]>(`${this.api}/all`);
  }

  protected abstract getEndpoint(): string;

  private buildSearchParams(searchModel: BaseSearchModel) {
    let params = new HttpParams()
      .set('pageSize', searchModel.pageSize)
      .set('pageNum', searchModel.pageNum)
      .set('sort', searchModel.sort)
      .set('dir', searchModel.dir);

    searchModel.fields.forEach(field => {
      if (_.isDate(field.value)) {
        params = params.set(field.name, formatDate(field.value, 'dd/MM/yyyy HH:mm:ss', 'en-GB'));
      } else if (!_.isEmpty(field.value)) {
        params = params.set(field.name, field.value);
      }
    });
    return params;
  }
}
