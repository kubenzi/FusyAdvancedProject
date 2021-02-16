import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {User} from '../../models/models';
import {Observable} from 'rxjs';
import {filter} from 'rxjs/operators';
import {NavigationStart, Router, RouterEvent} from '@angular/router';
import {AuthService} from '../../authentication/services/auth.service';


@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  user$: Observable<User>;

  constructor(private userService: UserService, private activatedRoute: Router, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.user$ = this.userService.getUser$();
    this.activatedRoute.events.pipe(
      filter(e => e instanceof NavigationStart)
    ).subscribe((e: RouterEvent) => {
      this.userService.setAddress(e.url);
      console.log(e.url + ' sect comp');
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

  getInitial(){
    return this.userService.user$.getValue().firstName.charAt(0);
  }
}
