import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../authentication/services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  unamePattern = "^[a-z0-9_-]{8,15}$";
  email = new FormControl('', [Validators.required, Validators.email]);

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
       username: ['', Validators.pattern(this.unamePattern)],
       password: [''],
       email: this.email,
       firstName: [''],
       lastName: ['']
    });
  }

  get form() { return this.registerForm.controls; }

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  get username() {
    return this.registerForm.get('username');
  }

  get firstName() {
    return this.registerForm.get('firstName');
  }

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
