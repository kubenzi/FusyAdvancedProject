import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {of, Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {catchError, mapTo, tap} from 'rxjs/operators';
import {LoginData} from '../models/login-data';
import { Cookie } from 'ng2-cookies';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly SESSION_ID = 'SESSION_ID';
  private readonly USER_ID = 'USER_ID';
  private loggedUser: string;
  private userId: number;

  constructor(private http: HttpClient) { }

  register(user: { username: string, password: string, firstName: string, lastName: string, email: string }): Observable<boolean> {
    return this.http.post<any>(`${environment.apiUrl}/register`, user)
      .pipe(
        mapTo(true),
        catchError(error => {
          alert(error.error);
          return of(false);
        }));
  }

  login(user: { username: string, password: string }): Observable<boolean> {
    return this.http.post<any>(`${environment.apiUrl}/login`, user)
      .pipe(
        tap((data: LoginData) => this.doLoginUser(data.username, data.sessionId, data.userId)),
        mapTo(true),
        catchError(error => {
          console.log(error);
          return of(false);
        }));
  }

  logout() {
    return this.http.post<any>(`${environment.apiUrl}/log-out`, {}).pipe(
      tap(() => this.doLogoutUser()),
      mapTo(true),
      catchError(error => {
        this.doLogoutUser();
        alert(error.error);
        return of(false);
      }));
  }

  isLoggedIn() {
    return !!this.getSessionId();
  }

  getSessionId() {
    return localStorage.getItem(this.SESSION_ID);
  }

  private doLoginUser(username: string, sessionId: string, userId: number) {
    this.loggedUser = username;
    this.storeSessionId(sessionId);
    Cookie.set("JSESSIONID", sessionId);
    this.storeUserId(userId);
  }

  private doLogoutUser() {
    this.loggedUser = null;
    this.removeSessionId();
    Cookie.delete("JSESSIONID", this.SESSION_ID);
    this.removeUserId();
  }

  private storeSessionId(sessionId: string) {
    localStorage.setItem(this.SESSION_ID, sessionId);
  }

  private removeSessionId() {
    localStorage.removeItem(this.SESSION_ID);
  }

  getUserId() {
    return +localStorage.getItem(this.USER_ID);
  }

  private storeUserId(userId: number) {
    localStorage.setItem(this.USER_ID, `${userId}`);
  }

  private removeUserId() {
    localStorage.removeItem(this.USER_ID);
  }

}
