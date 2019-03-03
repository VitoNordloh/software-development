import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SearchComponent} from './search/search/search.component';
import {UniversityComponent} from './universities/university/university.component';

const routes: Routes = [
  { path: 'search', component: SearchComponent },
  { path: 'universities/:id', component: UniversityComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
