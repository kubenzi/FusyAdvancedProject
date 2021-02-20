import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {switchMap, tap} from 'rxjs/operators';
import {Data, Series, User, Account, Category} from '../models/models';
import {AuthService} from '../authentication/services/auth.service';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  user$ = new BehaviorSubject<User>({});
  flag = new BehaviorSubject<boolean>(true);
  address: string;
  addressChange$ = new Subject<boolean>();


  constructor(private http: HttpClient, private authService: AuthService) {
  }

  setUser$(userId: number): Observable<User> {
    const url = 'http://localhost:8080/api/v1/users/' + userId;
    return this.http.get<User>(url).pipe(
      tap(user => this.user$.next(user))
    );
  }

  getAddressChange$(): Observable<boolean> {
    return this.addressChange$.asObservable();
  }

  reEmitUser(): void {
    this.user$.next(this.user$.value);
  }

  getUser$(): Observable<User> {
    return this.user$.asObservable();
  }

  getLineChartDataForInit30(): Observable<Data[]> {
    const url = 'http://localhost:8080/api/v1/users/' + +'/line-chart/' + 30;
    return this.http.get<Data[]>(url);
  }

  getLineChartDataForPeriod(addressUrl: string, period: number): Observable<Data[]> {
    if (addressUrl === undefined) {
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/line-chart/' + period;
      return this.flag.asObservable().pipe(
        switchMap(() => this.http.get<Data[]>(url))
      )
      // return this.http.get<Data[]>(url);
    }
    if (addressUrl === '/dashboard') {
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/line-chart/' + period;
      return this.flag.asObservable().pipe(
        switchMap(() => this.http.get<Data[]>(url))
      )
    }
    if (addressUrl === '/scheduled') {
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/line-chart/' + period;
      return this.flag.asObservable().pipe(
        switchMap(() => this.http.get<Data[]>(url))
      )
    } else { //if addressUrl = login
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/line-chart' + addressUrl + '/period/' + period;
      return this.flag.asObservable().pipe(
        switchMap(() => this.http.get<Data[]>(url))
      )
    }
  }

  getPieChartDataStart(): Observable<Series[]> {
    const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/pie-chart/30';
    return this.http.get<Series[]>(url);
  }

  getPieChartDataStartForPeriod(addressUrl: string, period: number): Observable<Series[]> {
    if (addressUrl === undefined) {
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/pie-chart/' + period;
      return this.http.get<Series[]>(url);
    }
    if (addressUrl === '/dashboard') {
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/pie-chart/' + period;
      return this.http.get<Series[]>(url);
    }
    if (addressUrl === '/scheduled') {
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/pie-chart/' + period;
      return this.http.get<Series[]>(url);
    } else {
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/pie-chart' + addressUrl + '/period/' + period;
      return this.http.get<Series[]>(url);
    }
  }

  setAddress(addressUrl: string): void {
    this.address = addressUrl;
    this.addressChange$.next(true);
  }

  getAccountInfo(userId: number, accountId: number): Observable<Account>{
    return this.http.get<Account>('http://localhost:8080/api/v1/users/' + userId + '/accounts/' + accountId);
  }

  getCategoryInfo(userId: number, categoryId: number): Observable<Category>{
    return this.http.get<Category>('http://localhost:8080/api/v1/users/' + userId + '/categories/' + categoryId);
  }

  deleteOperation(userId: number, operationId: number) {
    return this.http.delete('http://localhost:8080/api/v1/users/' + userId + '/operations/' + operationId)
      .subscribe(() => console.log("operation deleted"));
  }
}
