import {Component} from '@angular/core';
import {Product} from "../domain/product";
import {ProductService} from "../service/product.service";
import {TableBase} from "../table-base";
import {ProductSearchModel} from "../domain/product-search-model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent extends TableBase<Product> {
  displayedColumns: string[] = ['name', 'note', 'code', 'actions'];

  constructor(public router: Router,
              public productService: ProductService) {
    super(productService);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  initSearchModel(): ProductSearchModel {
    return new ProductSearchModel();
  }

  async edit(obj: Product) {
    await this.router.navigate([`/product-detail/${obj.id}`]);
  }

  async add() {
    await this.router.navigate(['/product-detail']);
  }
}
