import {Component} from '@angular/core';
import {TableBase} from "../table-base";
import {Router} from "@angular/router";
import {OrderService} from "../service/order.service";
import {Order} from "../domain/order";
import {OrderSearchModel} from "../domain/order-search-model";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent extends TableBase<Order> {
  displayedColumns: string[] = ['createDate', 'client', 'address', 'actions'];

  constructor(public router: Router,
              public orderService: OrderService) {
    super(orderService);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  initSearchModel(): OrderSearchModel {
    return new OrderSearchModel();
  }

  async edit(obj: Order) {
    await this.router.navigate([`/order-detail/${obj.id}`]);
  }

  async add() {
    await this.router.navigate(['/order-detail']);
  }
}
