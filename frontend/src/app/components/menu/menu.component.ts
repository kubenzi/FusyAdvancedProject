import {Component, Input, OnInit} from '@angular/core';
import {Account, Category, User, UserService} from '../../services/user-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],

})
export class MenuComponent implements OnInit {

  // categories: Category[];
  // accounts: Account[];
  user: User;

  constructor(private router: Router, private userService: UserService) {
    // this.categories = this.router.getCurrentNavigation().extras.state.categories;
    // this.accounts = this.router.getCurrentNavigation().extras.state.accounts;

  }

  ngOnInit(): void {
    this.user = this.userService.getUser();
  }

}
