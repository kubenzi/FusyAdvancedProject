import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {AccountType, Category, Currency, DialogData, Operation, User} from '../../models/models';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../services/user-service';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../../authentication/services/auth.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-dialog-form',
  templateUrl: './dialog-form.component.html',
  styleUrls: ['./dialog-form.component.css']
})
export class DialogFormComponent {

  addForm: FormGroup;
  accountNamePattern = '';
  balancePattern = '';
  accountNumberPattern = '';
  accountTypeIdPattern = '';
  currencyIdPattern = '';

  name = new FormControl('', [Validators.required, Validators.pattern(this.accountNamePattern)]);
  balance = new FormControl('', [Validators.required, Validators.pattern(this.balancePattern)]);
  accountNumber = new FormControl('', [Validators.required, Validators.pattern(this.accountNumberPattern)]);
  accountTypeId = new FormControl('', [Validators.required, Validators.pattern(this.accountTypeIdPattern)]);
  currencyId = new FormControl('', [Validators.required, Validators.pattern(this.currencyIdPattern)]);
  currencies$: Currency[];
  accountTypes$: AccountType[];
  user$: Observable<User>;



  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private http: HttpClient,
              public dialogRef: MatDialogRef<DialogFormComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData)
  {

  }

  ngOnInit(){
    this.addForm = this.formBuilder.group({
      name: this.name,
      balance: this.balance,
      accountNumber: this.accountNumber,
      accountTypeId: this.accountTypeId,
      currencyId: this.currencyId,
      }
    );
    this.setAccountTypes$();
    this.setCurrencies$();
    this.user$ = this.userService.getUser$();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  get form() { return this.addForm.controls; }

  setCurrencies$(): void{
    const url = 'http://localhost:8080/api/v1/currencies';
    this.http.get<Currency[]>(url).subscribe(value => this.currencies$ = value);
  }

  setAccountTypes$(): void{
    const url = 'http://localhost:8080/api/v1/account-types';
    this.http.get<AccountType[]>(url).subscribe(value => this.accountTypes$ = value);
  }


  addAccount(){
    this.userService.addAccount(
      {
        name: this.form.name.value,
        balance: this.form.balance.value,
        accountNumber: this.form.accountNumber.value,
        accountTypeId: this.form.accountTypeId.value,
        currencyId: this.form.currencyId.value
      }
    )
      .subscribe((account) => {
        console.log('account added');
        console.log(this.accountTypeId);
      })
    this.dialogRef.close();
    window.location.reload();
  }

}
