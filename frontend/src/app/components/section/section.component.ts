import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {Bank, Operation, User} from '../../models/models';
import {Observable} from 'rxjs';
import {filter} from 'rxjs/operators';
import {NavigationStart, Router, RouterEvent} from '@angular/router';
import {AuthService} from '../../authentication/services/auth.service';
import {HttpClient} from "@angular/common/http";


@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  user$: Observable<User>;
  totalBalance: number;
  lastOperations: Operation[] = [];

  constructor(private userService: UserService, private activatedRoute: Router,
              private authService: AuthService, private router: Router, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.user$ = this.userService.getUser$();
    this.activatedRoute.events.pipe(
      filter(e => e instanceof NavigationStart)
    ).subscribe((e: RouterEvent) => {
      this.userService.setAddress(e.url);
      console.log(e.url + ' sect comp');
    });
    this.userService.getTotalBalance().subscribe(
      value => this.totalBalance = value
    );
    this.getLastOperations$();
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

  getInitial() {
    return this.userService.user$.getValue()?.firstName.charAt(0);
  }
  // http://localhost:8080/api/v1/users/27/operations?lastOperation=4

  getLastOperations$(): void {
    const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/operations?lastOperation=4';
    this.http.get<Operation[]>(url).subscribe(value => this.lastOperations = value);
  }
}
