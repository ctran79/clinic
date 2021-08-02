import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {MatDividerModule} from "@angular/material/divider";
import {MatMenuModule} from "@angular/material/menu";
import {ProductListComponent} from './product-list/product-list.component';
import {ProductDetailComponent} from './product-detail/product-detail.component';
import {OrderDetailComponent} from './order-detail/order-detail.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {HttpClientModule} from "@angular/common/http";
import {MatTableModule} from "@angular/material/table";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatIconModule} from "@angular/material/icon";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {TableFilterComponent} from './table-filter/table-filter.component';
import {MatGridListModule} from "@angular/material/grid-list";
import {OrderItemDialogComponent} from './order-item-dialog/order-item-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatSelectModule} from "@angular/material/select";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {OrderListComponent} from './order-list/order-list.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MAT_DATE_LOCALE, MatNativeDateModule} from "@angular/material/core";
import {NgxPrintModule} from "ngx-print";
import {OrderPrintComponent} from './order-print/order-print.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ProductListComponent,
    ProductDetailComponent,
    OrderDetailComponent,
    TableFilterComponent,
    OrderItemDialogComponent,
    OrderListComponent,
    OrderPrintComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatDialogModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatDividerModule,
    MatMenuModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    HttpClientModule,
    MatTableModule,
    MatButtonToggleModule,
    MatIconModule,
    MatPaginatorModule,
    MatSortModule,
    FormsModule,
    MatGridListModule,
    MatSelectModule,
    MatAutocompleteModule,
    MatDatepickerModule,
    NgxPrintModule
  ],
  providers: [{provide: MAT_DATE_LOCALE, useValue: 'en-GB'}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
