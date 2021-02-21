import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AccountType, Currency, DialogData, User} from '../../models/models';
import {Observable} from 'rxjs';
import {UserService} from '../../services/user-service';
import {HttpClient} from '@angular/common/http';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-form-category',
  templateUrl: './dialog-form-category.component.html',
  styleUrls: ['./dialog-form-category.component.css']
})
export class DialogFormCategoryComponent implements OnInit {

  addCategoryForm: FormGroup;
  categoryNamePattern = '';
  categoryName = new FormControl('', [Validators.required, Validators.pattern(this.categoryNamePattern)]);
  user$: Observable<User>;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    public dialogRef: MatDialogRef<DialogFormCategoryComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData)
  { }

  ngOnInit(): void {
    this.addCategoryForm = this.formBuilder.group({
      name: this.categoryName
    })
    this.user$ = this.userService.getUser$();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  get form() { return this.addCategoryForm.controls; }

  addCategory(){
    this.userService.addCategory(
      {
        name: this.form.name.value
      }
    )
      .subscribe((category) => {
        console.log('category added');
      })
    this.dialogRef.close();
    window.location.reload();
  }

}
