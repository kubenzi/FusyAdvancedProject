import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {AuthService} from '../../authentication/services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
       username: [''],
       password: [''],
       email: [''],
       firstName: [''],
       lastName: ['']
    });
  }

  get form() { return this.registerForm.controls; }

  register() {
    this.authService.register(
      {
        username: this.form.username.value,
        password: this.form.password.value,
        firstName: this.form.firstName.value,
        lastName: this.form.lastName.value,
        email: this.form.email.value
      }
    )
      .subscribe(success => {
        if (success) {
          console.log('kubunia rejestracja');
          this.router.navigate(['/login']);
        }
      });
  }
}
