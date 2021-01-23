import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';


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
}

export interface OperationType {
  id: number;
  name: string;
}

export interface Operation {
  id: number;
  description: string;
  value: number;
  date: string;
  operationType: OperationType;
}

export interface Category {
  id: number;
  name: string;
  operations: Operation[];
}

export interface AccountType {
  id: number;
  name: string;
}

export interface Currency {
  id: number;
  name: string;
  signature: string;
}

export interface Account {
  id: number;
  name: string;
  balance: number;
  accountNumber: string;
  accountType: AccountType;
  currency: Currency;
  operations: Operation[];
}

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  categories: Category[];
  accounts: Account[];
}
