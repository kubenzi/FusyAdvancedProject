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
    this.userService.getData().subscribe(value => this.lineChartData = value);
    this.userService.getPieChartData().subscribe(value => this.pieChartData = value);
  }

  ngOnInit(): void {
    this.user = this.userService.getUser();
  }
}
