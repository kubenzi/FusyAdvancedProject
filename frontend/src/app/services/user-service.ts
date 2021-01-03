import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {USER_MOCK} from '../user-mock';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient ) { }


  // public getUser(userId): Observable<User> {
  //   return this.http.get<User>('http://localhost:8080/api/v1/users/1');
  // }

  public getUser(userId): User {
    return USER_MOCK;
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
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  categories: Category[];
  accounts: Account[];
}
