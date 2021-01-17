import { Component, OnInit } from '@angular/core';
import { UserService, User, AccountType, Currency, Operation, OperationType}
from '../../services/user-service';

@Component({
  selector: 'app-user-component',
  templateUrl: './user-component.component.html',
  styleUrls: ['./user-component.component.css']
})
export class UserComponentComponent implements OnInit {

  user: User;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUser()
      .subscribe(value => this.user = value);
  }
}
