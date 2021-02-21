import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Account, Category, User} from '../../models/models';
import {UserService} from '../../services/user-service';
import {ActivatedRoute} from '@angular/router';
import {filter, switchMap} from 'rxjs/operators';
import {AuthService} from '../../authentication/services/auth.service';
import {HttpClient} from '@angular/common/http';
import {DialogFormCategoryComponent} from '../dialog-form-category/dialog-form-category.component';
import {MatDialog} from '@angular/material/dialog';
import {DialogFormNewOperationComponent} from '../dialog-form-new-operation/dialog-form-new-operation.component';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {

  user$: Observable<User>;
  categories$: Category[];
  account: Observable<Account>;
  accountId: number;

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private authService: AuthService,
              private http: HttpClient,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.accountId = Number(this.route.snapshot.paramMap.get('id'));
    this.user$ = this.userService.getUser$();
    this.setCategories$();
    this.account = this.userService.getUser$().pipe(
      filter(user => !!user),
      switchMap(
        user$ => this.userService.getAccountInfo(user$.id, this.accountId)
      )
    );
  }

  setCategories$(): void {
    const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/categories';
    this.http.get<Category[]>(url).subscribe(value => this.categories$ = value);
  }


  openDialogOperation(): void {
    const dialogRef = this.dialog.open(DialogFormNewOperationComponent, {
      width: '250px', data: {
        accountId: this.accountId
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.userService.reEmitUser();
      // this.userService.flag.next(true);
    });
  }
}
