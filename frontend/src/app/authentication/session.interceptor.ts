import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from './services/auth.service';

@Injectable()
export class SessionInterceptor implements HttpInterceptor {

  constructor(public authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.authService.getSessionId()) {
      request = this.addSessionId(request, this.authService.getSessionId(), this.authService.getUserId());
    }
    return next.handle(request);
  }

  private addSessionId(request: HttpRequest<any>, sessionId: string, userId: number) {
    return request.clone({
      setHeaders: {
        // 'Authorization': `Bearer ${sessionId}`,
        'JSESSIONID': 'JSESSIONID=' + `${sessionId}`,
        'UserID': `${userId}`,
      }
    });
  }
}
