import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PrescriptionDetailComponent} from "./prescription-detail/prescription-detail.component";
import {DrugListComponent} from "./drug-list/drug-list.component";
import {DrugDetailComponent} from "./drug-detail/drug-detail.component";
import {PrescriptionListComponent} from "./prescription-list/prescription-list.component";
import {DictionaryComponent} from "./dictionary/dictionary.component";
import {PatientListComponent} from "./patient-list/patient-list.component";

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
