import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MenuComponent} from './components/menu/menu.component';
import {CategoriesComponent} from './components/categories/categories.component';
import {ScheduledComponent} from './components/scheduled/scheduled.component';
import {SectionComponent} from './components/section/section.component';
import {LoginComponent} from './components/login/login.component';
import {AuthGuard} from './authentication/guards/auth.guard';
import {RegisterComponent} from './components/register/register.component';
import {AccountsComponent} from './components/accounts/accounts.component';

const routes: Routes = [
  {path: 'dashboard', component: MenuComponent},
  {path: 'navigation', component: SectionComponent, outlet: 'nav'},
  {path: 'scheduled', component: ScheduledComponent},
  {path: 'categories/:id', component: CategoriesComponent},
  {path: 'accounts/:id', component: AccountsComponent},
  {path: '', pathMatch: 'full', redirectTo: '/login' },
  {path: 'login', component: LoginComponent, canActivate: [AuthGuard]},
  {path: 'register', component: RegisterComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
