import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {Data, Series, User} from '../../models/models';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],

})
export class MenuComponent implements OnInit {

  user: User;
  lineChartData: Data[];
  pieChartData: Series[] = [];

  constructor(private userService: UserService) {

  }

  ngOnInit(): void {
    this.user = this.userService.getUser();
  }
}
