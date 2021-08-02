import {Component, Input, OnInit} from '@angular/core';
import {Order} from "../domain/order";
import {MatTableDataSource} from "@angular/material/table";
import {OrderItem} from "../domain/order-item";

@Component({
  selector: 'app-order-print',
  templateUrl: './order-print.component.html',
  styleUrls: ['./order-print.component.css']
})
export class OrderPrintComponent implements OnInit {

  displayedColumns: string[] = ['seqNo', 'product'];
  itemDataSource = new MatTableDataSource<OrderItem>([]);

  _order!: Order;

  @Input()
  set order(order: Order) {
    this._order = order;
    this.itemDataSource = new MatTableDataSource(order.items);
  };

  get order(): Order {
    return this._order;
  }

  constructor() { }

  ngOnInit(): void {
  }
}
