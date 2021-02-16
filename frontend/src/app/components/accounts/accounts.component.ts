import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {User} from '../../models/models';
import {UserService} from '../../services/user-service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  user$: Observable<User>;
  account: Observable<Account>;

  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    // this.accountId = Number(this.route.snapshot.paramMap.get('id'));
    this.user$ = this.userService.getUser$();
  }

}
