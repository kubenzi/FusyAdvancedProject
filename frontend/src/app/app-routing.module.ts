import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MenuComponent} from './components/menu/menu.component';

const routes: Routes = [
  {path: 'user', component: MenuComponent},
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
