import {Component, Input, OnInit} from '@angular/core';
import {Account, Category, UserService} from '../../services/user-service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],

})
export class MenuComponent implements OnInit {

  @Input() categories: Category[];
  @Input() accounts: Account[];


  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
  }

}
