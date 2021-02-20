import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Account, Category, User} from '../../models/models';
import {UserService} from '../../services/user-service';
import {ActivatedRoute} from '@angular/router';
import {filter, switchMap} from 'rxjs/operators';
import {AuthService} from '../../authentication/services/auth.service';
import {HttpClient} from '@angular/common/http';

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
              private http: HttpClient) {
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

}
