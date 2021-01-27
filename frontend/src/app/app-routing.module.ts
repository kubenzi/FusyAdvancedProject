import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MenuComponent} from './components/menu/menu.component';
import {LoginComponent} from './components/login/login.component';
import {AuthGuard} from './authentication/guards/auth.guard';
import {RegisterComponent} from './components/register/register.component';

const routes: Routes = [
  // { path: '', pathMatch: 'full', redirectTo: '/login' },
  // {
  //   path: 'login', component: LoginComponent, canActivate: [AuthGuard]
  // },
  // {
  //   path: 'register', component: RegisterComponent, canActivate: [AuthGuard]
  // },
  {
    path: 'user', component: MenuComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule], declarations: []
})
export class AppRoutingModule {
}
