import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OrderDetailComponent} from "./order-detail/order-detail.component";
import {ProductListComponent} from "./product-list/product-list.component";
import {ProductDetailComponent} from "./product-detail/product-detail.component";
import {OrderListComponent} from "./order-list/order-list.component";

const routes: Routes = [
  {
    path: 'order-detail', component: OrderDetailComponent
  },
  {
    path: 'order-detail/:id', component: OrderDetailComponent
  },
  {
    path: 'order-list', component: OrderListComponent
  },
  {
    path: 'product-detail', component: ProductDetailComponent
  },
  {
    path: 'product-detail/:id', component: ProductDetailComponent
  },
  {
    path: 'product-list', component: ProductListComponent
  },
  {
    path: '', redirectTo: '/order-detail', pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
