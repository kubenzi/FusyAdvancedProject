import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Data, Series, User} from '../models/models';
import {AuthService} from '../authentication/services/auth.service';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  user$ = new BehaviorSubject<User>({});
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
    const url = 'http://localhost:8080/api/v1/users/' +  + '/line-chart/' + 30;
    return this.http.get<Data[]>(url);
  }

  getLineChartDataForPeriod(addressUrl: string, period: number): Observable<Data[]> {
    if (addressUrl === undefined) {
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/line-chart/' + period;
      return this.http.get<Data[]>(url);
    }
    if (addressUrl === '/dashboard') {
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/line-chart/' + period;
      return this.http.get<Data[]>(url);
    }
    if (addressUrl === '/scheduled') {
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/line-chart/' + period;
      return this.http.get<Data[]>(url);
    } else { //if addressUrl = login
      const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/line-chart' + addressUrl + '/period/' + period;
      return this.http.get<Data[]>(url);
    }
  }


  getPieChartDataStart(): Observable<Series[]> {
    const url = 'http://localhost:8080/api/v1/users/' + this.authService.getUserId() + '/pie-chart/30';
    return this.http.get<Series[]>(url);
  }

  // getPieChartDataStartForPeriod(period: number): Observable<Series[]> {
  //   const url = 'http://localhost:8080/api/v1/users/' + '2' + '/pie-chart/' + period;
  //   return this.http.get<Series[]>(url);
  // }
  //
  // getPieChartData(addressUrl: string): Observable<Series[]> {
  //   console.log(addressUrl);
  //   if (addressUrl === undefined) {
  //     const url = 'http://localhost:8080/api/v1/users/' + '2' + '/pie-chart/30';
  //     return this.http.get<Series[]>(url);
  //   } else {
  //     const url = 'http://localhost:8080/api/v1/' + addressUrl + '/pie-chart/60';
  //     return this.http.get<Series[]>(url);
  //   }
  // }

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
      const url = 'http://localhost:8080/api/v1/users/'+ this.authService.getUserId() +'/pie-chart' + addressUrl + '/period/' + period;
      return this.http.get<Series[]>(url);
    }
  }

  setAddress(addressUrl: string): void {
    this.address = addressUrl;
    this.addressChange$.next(true);
  }

  // getAddress(): string {
  //   console.log(this.address + 'test of user service');
  //   return this.address;
  // }
}
