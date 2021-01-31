import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {Data, Series, User} from '../../models/models';
import {Observable} from 'rxjs';
import {ActivatedRoute, ActivationStart, NavigationStart, Router} from '@angular/router';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],

})
export class MenuComponent implements OnInit {

  user$: Observable<User>;
  lineChartData: Data[];
  pieChartData: Series[] = [];

  constructor(private userService: UserService, private activatedRoute: Router) {

  }

  ngOnInit(): void {
    this.user$ = this.userService.getUser$();
    this.userService.reEmitUser();
    this.activatedRoute.events.pipe(first(event => !!event)).subscribe((
      navigationStart: NavigationStart) => this.userService.setAddress(navigationStart.url));
    this.userService.getAddress();

      // navigationStart: NavigationStart) => console.log(navigationStart.url));
  }
}
