import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/registration/registration.component';
import {AuthGuard} from './guards/authentication.guard';
import {AuthenticationService} from './services/authentication.service';
import {UsersGuard} from './guards/users.guard';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {SessionInterceptor} from './session.interceptor';
import {RouterModule} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [LoginComponent, RegisterComponent],
  providers: [
    AuthGuard,
    AuthenticationService,
    UsersGuard,
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
    ReactiveFormsModule
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule
  ]
})

export class AuthenticationModule { }
