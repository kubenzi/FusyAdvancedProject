import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {AuthService} from '../../authentication/services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  get form() { return this.loginForm.controls; }

  login(){
    this.authService.login(
      {
        username: this.form.username.value,
        password: this.form.password.value
      }
    )
      .subscribe(success => {
        if (success){
          this.router.navigate(['/dashboard']);
        }
      });
  }
}
