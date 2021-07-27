import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../service/product.service";
import {Order} from "../domain/order";
import {OrderService} from "../service/order.service";
import {MatTableDataSource} from "@angular/material/table";
import {OrderItem} from "../domain/order-item";
import {MatDialog} from "@angular/material/dialog";
import {OrderItemDialogComponent} from "../order-item-dialog/order-item-dialog.component";

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {
  displayedColumns: string[] = ['seqNo', 'product', 'note', 'actions'];
  form!: FormGroup;
  order!: Order;
  id: number = this.activatedRoute.snapshot.params.id;
  itemDataSource = new MatTableDataSource<OrderItem>([]);

  constructor(public formBuilder: FormBuilder,
              public router: Router,
              public dialog: MatDialog,
              public activatedRoute: ActivatedRoute,
              public productService: ProductService,
              public orderService: OrderService) {
  }

  ngOnInit(): void {
    this.createFormView();
    this.getData();
  }

  createFormView() {
    this.form = this.formBuilder.group({
      client: ['', Validators.required],
      address: [''],
    });
  }

  errorHandling(control: string, error: string) {
    return this.getControl(control).hasError(error);
  }

  onSubmit() {
    if (this.form.valid) {
      const order = this.createObject();
      this.orderService.saveModel(order).subscribe(order => this.navigateToList());
    }
  }

  getControl(name: string): AbstractControl {
    return this.form.controls[name];
  }

  createObject() {
    this.order = {
      id: this.id,
      client: this.getControl('client').value,
      address: this.getControl('address').value,
      items: this.itemDataSource.data
    };
    return this.order;
  }

  getData() {
    if (this.id) {
      this.orderService.getModel(this.id).subscribe(order => this.patchFormValue(order));
    }
  }

  patchFormValue(order: Order) {
    this.order = order;
    this.form.patchValue({
      client: order.client,
      address: order.address,
    });

    this.itemDataSource = new MatTableDataSource(order.items);
  }

  async navigateToList() {
    await this.router.navigate(['/order-list']);
  }

  edit(obj?: OrderItem) {
    const dialogRef = this.dialog.open(OrderItemDialogComponent, {
      width: '880px',
      height: '300px',
      data: obj
    });
    dialogRef.afterClosed().subscribe(newItem => {
      if (newItem) {
        const list = this.itemDataSource.data;
        if (newItem.seqNo < 0) {
          newItem.seqNo = this.itemDataSource.data.length + 1;
          list.push(newItem);
        } else {
          const idx = list.findIndex(orderItem => orderItem.seqNo === newItem.seqNo);
          list[idx] = newItem;
        }
        this.itemDataSource = new MatTableDataSource(list);
      }
    });
  }
}
