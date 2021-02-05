import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {User} from '../../models/models';
import {Observable} from 'rxjs';
import {filter, first} from 'rxjs/operators';
import {NavigationEnd, NavigationStart, Router, RouterEvent} from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  user$: Observable<User>;

  constructor(private userService: UserService, private activatedRoute: Router) {
  }

  ngOnInit(): void {
    this.user$ = this.userService.getUser$();
    // this.activatedRoute.events.pipe(first(event => !!event)).subscribe((
    //   navigationStart: NavigationStart) => this.userService.getPieChartData(navigationStart.url));
    // this.activatedRoute.events.pipe(first(event => !!event)).subscribe((
    //   navigationStart: NavigationStart) => this.userService.setAddress(navigationStart.url));
    this.activatedRoute.events.pipe(
      filter(e => e instanceof NavigationStart)
    ).subscribe((e: RouterEvent) => {
      this.userService.setAddress(e.url);
      console.log(e.url + ' navi comp');
    });
  }
}
