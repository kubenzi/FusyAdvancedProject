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
  unamePattern = "^[a-z0-9_-]{5,15}$";
  flnamePattern = "^[a-z0-9_-]{2,100}$";
  passwordPattern = "^[a-z0-9_-]{6,12}$"; //toImprove
  email = new FormControl('', [Validators.required, Validators.email]);
  username = new FormControl('', [Validators.required, Validators.pattern(this.unamePattern)]);
  password = new FormControl('');
  confirmPassword = new FormControl('');
  firstName = new FormControl('', [Validators.required, Validators.pattern(this.flnamePattern)]);
  lastName = new FormControl('', [Validators.required, Validators.pattern(this.flnamePattern)]);

  constructor(private authService: AuthService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
       username: this.username,
       password: this.password,
       email: this.email,
       firstName: this.firstName,
       lastName: this.lastName,
       confirmPassword: this.confirmPassword,
    });
  }

  get form() { return this.registerForm.controls; }

  getErrorMessageEmail() {
    if (this.email.hasError('required')) {
      return 'You must enter an e-mail';
    }
    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  getErrorMessageUsername() {
    if (this.username.hasError('required')) {
      return 'You must enter an username';
    }
    return this.username.hasError('pattern') ? 'Username length range: 5 - 15' : '';
  }

  getErrorMessagePassword() {
    if (this.password.hasError('required')) {
      return 'You must enter a password';
    }
    return this.password.hasError('pattern') ? 'Password length range: 6 - 12' : '';
  }

  getErrorMessageConfirmPassword() {
    if (this.confirmPassword.hasError('required')) {
      return 'Confirm password';
    }
    return this.registerForm.get(['password']).value != this.registerForm.get(['confirmPassword']).value ? 'Password do not match' : '';
  }

  getErrorMessageFirstName() {
    if (this.username.hasError('required')) {
      return 'You must enter first name';
    }
  }

  getErrorMessageLastName() {
    if (this.username.hasError('required')) {
      return 'You must enter last name';
    }
  }

  // checkPasswords(group: FormGroup) { // here we have the 'passwords' group
  //   const password = group.get('password').value;
  //   const confirmPassword = group.get('confirmPassword').value;
  //
  //   return password === confirmPassword ? null : { notSame: true }
  // }


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
