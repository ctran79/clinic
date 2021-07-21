import { Component, OnInit } from '@angular/core';
import {Product} from "../domain/product";
import {ProductService} from "../service/product.service";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  displayedColumns : string[] = ['code', 'name', 'note'];
  dataSource = new MatTableDataSource<Product>();

  constructor(public productService: ProductService) { }

  ngOnInit(): void {
  }

  addData() {

  }
}
