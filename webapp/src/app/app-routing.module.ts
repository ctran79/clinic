import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PrescriptionDetailComponent} from "./component/prescription-detail/prescription-detail.component";
import {DrugListComponent} from "./component/drug-list/drug-list.component";
import {DrugDetailComponent} from "./component/drug-detail/drug-detail.component";
import {PrescriptionListComponent} from "./component/prescription-list/prescription-list.component";
import {DictionaryComponent} from "./component/dictionary/dictionary.component";
import {PatientListComponent} from "./component/patient-list/patient-list.component";
import {PatientDetailComponent} from "./component/patient-detail/patient-detail.component";

const routes: Routes = [
  {
    path: 'dictionary', component: DictionaryComponent
  },
  {
    path: 'prescription-detail', component: PrescriptionDetailComponent
  },
  {
    path: 'prescription-detail/:id', component: PrescriptionDetailComponent
  },
  {
    path: 'patient-list', component: PatientListComponent
  },
  {
    path: 'patient-detail', component: PatientDetailComponent
  },
  {
    path: 'patient-detail/:id', component: PatientDetailComponent
  },
  {
    path: 'drug-detail', component: DrugDetailComponent
  },
  {
    path: 'drug-detail/:id', component: DrugDetailComponent
  },
  {
    path: 'drug-list', component: DrugListComponent
  },
  {
    path: '', redirectTo: '/patient-list', pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
