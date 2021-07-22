import { Injectable } from '@angular/core';
import {CrudService} from "./crud.service";
import {Product} from "../domain/product";
import {HttpClient} from "@angular/common/http";
import {from, Observable} from "rxjs";
import {BaseSearchModel} from "../domain/base-search-model";

@Injectable({
  providedIn: 'root'
})
export class ProductService extends CrudService<Product>{

  constructor(protected http: HttpClient) {
    super(http);
  }

  protected getEndpoint(): string {
    return 'product';
  }

  search(searchModel: BaseSearchModel): Observable<Product[]> {
    const retVal = [{
      id: 1,
      code: 'code1',
      name: 'name1',
      note: 'Tắm hàng ngày'
    } as Product];
    return from([retVal]);
  }
}
