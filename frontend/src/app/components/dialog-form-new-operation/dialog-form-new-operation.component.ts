import {Component, Inject, Input, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Category, Currency, DialogData, User} from '../../models/models';
import {Observable} from 'rxjs';
import {UserService} from '../../services/user-service';
import {HttpClient} from '@angular/common/http';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {AuthService} from '../../authentication/services/auth.service';
import {ActivatedRoute} from '@angular/router';
import {AccountsComponent} from '../accounts/accounts.component';

@Component({
  selector: 'app-dialog-form-new-operation',
  templateUrl: './dialog-form-new-operation.component.html',
  styleUrls: ['./dialog-form-new-operation.component.css']
})
export class DialogFormNewOperationComponent implements OnInit {

  addSingleTransactionForm: FormGroup;
  descriptionPattern = '';
  valuePattern = '';
  datePattern = '';
  accountIdPattern = '';
  categoryIdPattern = '';

  description = new FormControl('', [Validators.required, Validators.pattern(this.descriptionPattern)]);
  value = new FormControl('', [Validators.required, Validators.pattern(this.valuePattern)]);
  date = new FormControl('', [Validators.required, Validators.pattern(this.datePattern)]);
  categoryId = new FormControl('', [Validators.required, Validators.pattern(this.categoryIdPattern)]);

  categories$: Category[];
  user$: Observable<User>;



  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private http: HttpClient,
    private authService: AuthService,
    public dialogRef: MatDialogRef<DialogFormNewOperationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData)
  {

  }

  ngOnInit(): void {
    this.addSingleTransactionForm = this.formBuilder.group({
      description: this.description,
      value: this.value,
      date: this.date,
      categoryId: this.categoryId,
      accountId: this.data.accountId
    });
    this.setCategories$();
    this.user$ = this.userService.getUser$();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  get form() { return this.addSingleTransactionForm.controls; }

  setCategories$(): void {
    const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/categories';
    this.http.get<Category[]>(url).subscribe(value => this.categories$ = value);
  }

  addSingleTransaction(){

    this.userService.addSingleTransaction(
      {
        description: this.form.description.value,
        value: this.form.value.value,
        date: this.form.date.value,
        categoryId: this.form.categoryId.value,
        accountId: this.form.accountId.value
      }
    )
      .subscribe((operation) => {
        console.log('operation added');
      })
    this.dialogRef.close();
    window.location.reload();
  }

}
