import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, FormGroupName, ValidatorFn, Validators} from '@angular/forms';
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
  flnamePattern = "^[a-z0-9_-]{2,50}$";
  passwordPattern = "^[a-z0-9_-]{6,12}$";
  email = new FormControl('', [Validators.required, Validators.email]);
  username = new FormControl('', [Validators.required, Validators.pattern(this.unamePattern)]);
  password = new FormControl('', [Validators.required, Validators.pattern(this.passwordPattern)]);
  confirmPassword = new FormControl('', [Validators.required]);
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
    }, {validator: this.checkIfMatchingPasswords('password', 'confirmPassword')});
  }

  get form() { return this.registerForm.controls; }

  checkIfMatchingPasswords(passwordKey: string, passwordConfirmationKey: string) {
    return (group: FormGroup) => {
      let passwordInput = group.controls[passwordKey],
        passwordConfirmationInput = group.controls[passwordConfirmationKey];
      if (passwordInput.value !== passwordConfirmationInput.value) {
        return passwordConfirmationInput.setErrors({notEquivalent: true})
      }
      else {
        return passwordConfirmationInput.setErrors(null);
      }
    }
  }

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
