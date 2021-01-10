import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from './authentication/components/login/login.component';
import {AuthGuard} from './authentication/guards/authentication.guard';
import {RegisterComponent} from './authentication/components/registration/registration.component';
import {UsersGuard} from './authentication/guards/users.guard';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/login' },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'customers',
    loadChildren: './customers/customers.module#CustomersModule',
    canActivate: [UsersGuard],
    canLoad: [UsersGuard]
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
