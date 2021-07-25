import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {Product} from "../domain/product";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductService extends CrudService<Product> {

  constructor(protected http: HttpClient) {
    super(http);
  }

  protected getEndpoint(): string {
    return 'product';
  }
}
