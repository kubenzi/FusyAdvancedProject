import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Data, Series, User} from '../models/models';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  user$ = new BehaviorSubject<User>({});
  address: string;

  constructor(private http: HttpClient) {
  }

  setUser$(): Observable<User> {
    const url = 'http://localhost:8080/api/v1/users/2';
    return this.http.get<User>(url).pipe(
      tap(user => this.user$.next(user))
    );
  }

  reEmitUser(): void {
    this.user$.next(this.user$.value);
  }

  getUser$(): Observable<User> {
    return this.user$.asObservable();
  }

  getLineChartDataForInit30(): Observable<Data[]> {
    const url = 'http://localhost:8080/api/v1/users/' + '2' + '/line-chart/' + 30;
    return this.http.get<Data[]>(url);
  }

  getLineChartDataForPeriod(addressUrl: string, period: number): Observable<Data[]> {
    if (addressUrl === undefined) {
      const url = 'http://localhost:8080/api/v1/users/' + '2' + '/line-chart/' + period;
      return this.http.get<Data[]>(url);
    }
    if (addressUrl === '/dashboard') {
      const url = 'http://localhost:8080/api/v1/users/' + '2' + '/line-chart/' + period;
      return this.http.get<Data[]>(url);
    }
    if (addressUrl === '/scheduled') {
      const url = 'http://localhost:8080/api/v1/users/' + '2' + '/line-chart/' + period;
      return this.http.get<Data[]>(url);
    } else {
      const url = 'http://localhost:8080/api/v1/users/2/line-chart' + addressUrl + '/period/' + period;
      return this.http.get<Data[]>(url);
    }
  }


  getPieChartDataStart(): Observable<Series[]> {
    const url = 'http://localhost:8080/api/v1/users/' + '2' + '/pie-chart/30';
    return this.http.get<Series[]>(url);
  }

  getPieChartDataStartForPeriod(period: number): Observable<Series[]> {
    const url = 'http://localhost:8080/api/v1/users/' + '2' + '/pie-chart/' + period;
    return this.http.get<Series[]>(url);
  }

  getPieChartData(addressUrl: string): Observable<Series[]> {
    console.log(addressUrl);
    if (addressUrl === undefined) {
      const url = 'http://localhost:8080/api/v1/users/' + '2' + '/pie-chart/30';
      return this.http.get<Series[]>(url);
    } else {
      const url = 'http://localhost:8080/api/v1/' + addressUrl + '/pie-chart/60';
      return this.http.get<Series[]>(url);
    }
  }

  setAddress(addressUrl: string): void {
    this.address = addressUrl;
  }

  getAddress(): string {
    console.log(this.address + 'test of user service');
    return this.address;
  }
}
