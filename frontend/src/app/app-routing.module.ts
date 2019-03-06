import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SearchComponent} from './search/search/search.component';
import {UniversityComponent} from './universities/university/university.component';
import {DataComponent} from './data/data.component';

const routes: Routes = [
  { path: 'search', component: SearchComponent },
  { path: 'data', component: DataComponent },
  { path: 'universities/:id', component: UniversityComponent },
  {
    path: '',
    redirectTo: '/search',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    useHash: true
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
