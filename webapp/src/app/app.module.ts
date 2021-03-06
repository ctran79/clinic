import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import {HeaderComponent} from './component/header/header.component';
import {FooterComponent} from './component/footer/footer.component';
import {MatDividerModule} from "@angular/material/divider";
import {MatMenuModule} from "@angular/material/menu";
import {DrugListComponent} from './component/drug-list/drug-list.component';
import {DrugDetailComponent} from './component/drug-detail/drug-detail.component';
import {PrescriptionDetailComponent} from './component/prescription-detail/prescription-detail.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {HttpClientModule} from "@angular/common/http";
import {MatTableModule} from "@angular/material/table";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatIconModule} from "@angular/material/icon";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {TableFilterComponent} from './component/table-filter/table-filter.component';
import {MatGridListModule} from "@angular/material/grid-list";
import {IndicationDialogComponent} from './component/indication-dialog/indication-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatSelectModule} from "@angular/material/select";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {PrescriptionListComponent} from './component/prescription-list/prescription-list.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {DateAdapter, MAT_DATE_LOCALE, MatNativeDateModule} from "@angular/material/core";
import {DictionaryComponent} from './component/dictionary/dictionary.component';
import {PatientListComponent} from './component/patient-list/patient-list.component';
import {PatientAgePipe} from './pipeline/patient-age.pipe';
import {MatRadioModule} from "@angular/material/radio";
import {PatientDetailComponent} from './component/patient-detail/patient-detail.component';
import {DictionaryValueListComponent} from './component/dictionary-value-list/dictionary-value-list.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatListModule} from "@angular/material/list";
import {DiagnosisDialogComponent} from './component/diagnosis-dialog/diagnosis-dialog.component';
import {CustomDateAdapter} from "./utils/custom-date-adapter";
import {NotifierModule} from "angular-notifier";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DrugListComponent,
    DrugDetailComponent,
    PrescriptionDetailComponent,
    TableFilterComponent,
    IndicationDialogComponent,
    PrescriptionListComponent,
    DictionaryComponent,
    PatientListComponent,
    PatientAgePipe,
    PatientDetailComponent,
    DictionaryValueListComponent,
    DiagnosisDialogComponent,
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
    MatRadioModule,
    MatCheckboxModule,
    MatListModule,
    NotifierModule
  ],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'},
    {provide: DateAdapter, useClass: CustomDateAdapter}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
