import {Component} from '@angular/core';
import {Product} from "../domain/product";
import {ProductService} from "../service/product.service";
import {TableBase} from "../table-base";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent extends TableBase<Product> {
  displayedColumns: string[] = ['name', 'note', 'code', 'actions'];

  constructor(public productService: ProductService) {
    super(productService);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  edit(obj: Product) {
  }
}
