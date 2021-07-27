import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {HttpClient} from "@angular/common/http";
import {Order} from "../domain/order";

@Injectable({
  providedIn: 'root'
})
export class OrderService extends CrudService<Order> {

  constructor(protected http: HttpClient) {
    super(http);
  }

  protected getEndpoint(): string {
    return 'order';
  }
}
