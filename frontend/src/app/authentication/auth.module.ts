import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from '../components/login/login.component';
import {RegisterComponent} from '../components/register/register.component';
import {AuthGuard} from './guards/auth.guard';
import {AuthService} from './services/auth.service';
import {UserGuard} from './guards/user.guard';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {SessionInterceptor} from './session.interceptor';
import {RouterModule} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [LoginComponent, RegisterComponent],
  providers:[
    AuthGuard,
    AuthService,
    UserGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: SessionInterceptor,
      multi: true
    }
  ],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class AuthModule { }
