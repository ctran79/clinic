import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RecipeDetailComponent} from "./recipe-detail/recipe-detail.component";
import {DrugListComponent} from "./drug-list/drug-list.component";
import {DrugDetailComponent} from "./drug-detail/drug-detail.component";

const routes: Routes = [
  {
    path: 'recipe-detail', component: RecipeDetailComponent
  },
  {
    path: 'drug-list', component: DrugListComponent
  },
  {
    path: 'drug-detail', component: DrugDetailComponent
  },
  {
    path: 'drug-detail/:id', component: DrugDetailComponent
  },
  {
    path: '', redirectTo: '/recipe', pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
