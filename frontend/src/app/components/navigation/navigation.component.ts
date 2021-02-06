import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {User} from '../../models/models';
import {Observable} from 'rxjs';
import {first} from 'rxjs/operators';
import {NavigationStart, Router} from '@angular/router';
import {AuthService} from '../../authentication/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  user$: Observable<User>;

  constructor(private userService: UserService, private activatedRoute: Router, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.userService.setUser$().subscribe();
    this.user$ = this.userService.getUser$();
    // this.activatedRoute.events.pipe(first(event => !!event)).subscribe((
    //   navigationStart: NavigationStart) => this.userService.getPieChartData(navigationStart.url));
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
