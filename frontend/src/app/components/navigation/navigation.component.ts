import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {User} from '../../models/models';
import {BehaviorSubject, Observable} from 'rxjs';
import {AuthService} from '../../authentication/services/auth.service';
import {filter, first} from 'rxjs/operators';
import {NavigationEnd, NavigationStart, Router, RouterEvent} from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  user$: Observable<User>;
  username = new BehaviorSubject<User>({}).getValue().firstName;

  constructor(private userService: UserService, private activatedRoute: Router, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.userService.setUser$(this.authService.getUserId()).subscribe();
    this.user$ = this.userService.getUser$();
    console.log(this.user$);
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

  logout() {
    this.authService.logout()
      .subscribe(success => {
        if (success) {
          this.router.navigate(['/login']);
        } else {
          console.log('Logout not working');
        }
      });
  }
}
