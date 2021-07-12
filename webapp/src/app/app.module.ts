import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
 import {MatDividerModule} from "@angular/material/divider";
import {MatMenuModule} from "@angular/material/menu";
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
     ProductListComponent,
    ProductDetailComponent,
    OrderDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatDividerModule,
    MatMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
