import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Data, Series, User} from '../models/models';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  user: User;

  constructor(private http: HttpClient) {
  }

  setUser$(): Observable<User> {
    const url = 'http://localhost:8080/api/v1/users/1';
    return this.http.get<User>(url).pipe(
      tap(user => this.user = user)
    );
  }

  getUser(): User {
    return this.user;
  }

  getData30(): Observable<Data[]> {
    const url = 'http://localhost:8080/api/v1/users/' + '2' + '/line-chart/' + 30;
    return this.http.get<Data[]>(url);
  }

  getData(period: number): Observable<Data[]> {
    const url = 'http://localhost:8080/api/v1/users/' + '2' + '/line-chart/' + period;
    return this.http.get<Data[]>(url);
  }

  getPieChartData(): Observable<Series[]> {
    const url = 'http://localhost:8080/api/v1/users/' + '2' + '/pie-chart/30';
    return this.http.get<Series[]>(url);
  }
}
