import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {Data, Series, User} from '../../models/models';
import {Observable} from 'rxjs';
import {MatIconModule} from '@angular/material/icon';
import {ActivatedRoute, ActivationStart, NavigationStart, Router} from '@angular/router';
import {first} from 'rxjs/operators';
import {AuthService} from '../../authentication/services/auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],

})
export class MenuComponent implements OnInit {

  user$: Observable<User>;
  list: String[] = ["blue", "red", "gray", "black"];
  lineChartData: Data[];
  pieChartData: Series[] = [];

  constructor(private userService: UserService, private activatedRoute: Router) {

  }

  ngOnInit(): void {
    this.user$ = this.userService.getUser$();
    this.userService.reEmitUser();
    // this.activatedRoute.events.pipe(first(event => !!event)).subscribe((
    //   navigationStart: NavigationStart) => this.userService.setAddress(navigationStart.url));
    // this.userService.getAddress();h

      // navigationStart: NavigationStart) => console.log(navigationStart.url));
  }

}
