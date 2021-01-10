import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {catchError, mapTo, tap} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {LoginData} from '../model/login-data';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly SESSION_ID = 'SESSION_ID';
  private loggedUser: string;

  constructor(private http: HttpClient) {}

  register(user: { username: string, password: string }): Observable<boolean> {
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
        tap((data: LoginData) => this.doLoginUser(data.username, data.sessionId)),
        mapTo(true),
        catchError(error => {
          alert(error.error);
          return of(false);
        }));
  }

  logout() {
    return this.http.post<any>(`${environment.apiUrl}/logout`, {}).pipe(
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

  private doLoginUser(username: string, sessionId: string) {
    this.loggedUser = username;
    this.storeSessionId(sessionId);
  }

  private doLogoutUser() {
    this.loggedUser = null;
    this.removeSessionId();
  }

  private storeSessionId(sessionId: string) {
    localStorage.setItem(this.SESSION_ID, sessionId);
  }

  private removeSessionId() {
    localStorage.removeItem(this.SESSION_ID);
  }
}
