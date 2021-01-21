import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SectionComponent} from './components/section/section.component';

const routes: Routes = [
  {path: 'user', component: SectionComponent},
  // {path: 'dashboard', component: DashboardComponent},
  // {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  // {path: 'detail/:id', component: HeroDetailComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
