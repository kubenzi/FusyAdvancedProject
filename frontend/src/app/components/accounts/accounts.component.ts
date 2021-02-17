import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {User, Account, Operation} from '../../models/models';
import {UserService} from '../../services/user-service';
import {ActivatedRoute} from '@angular/router';
import {switchMap} from 'rxjs/operators';
import {MatSort, Sort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  user$: Observable<User>;
  account: Observable<Account>;
  accountId: number;
  // operations: Operation[];
  // sortedOperations: Operation[];

  constructor(private route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit(): void {
    this.accountId = Number(this.route.snapshot.paramMap.get('id'));
    this.user$ = this.userService.getUser$();
    this.account = this.user$.pipe(
      switchMap(
        user$ => this.userService.getAccountInfo(user$.id, this.accountId)
      )
    )
  }

  // getOp(accountId: number): Operation[] {
  //   return this.operations = this.account[accountId].operations;
  // }
  //
  // sortData(sort: Sort) {
  //   this.operations = this.getOp(this.accountId);
  //   const data = this.operations.slice();
  //   if (!sort.active || sort.direction === '') {
  //     this.sortedOperations = data;
  //     return;
  //   }
  //
  //   this.sortedOperations = data.sort((a, b) => {
  //     const isAsc = sort.direction === 'asc';
  //     switch (sort.active) {
  //       case a[0] : return this.compare(a.date, b.date, isAsc);
  //       // case 'calories': return compare(a.calories, b.calories, isAsc);
  //       // case 'fat': return compare(a.fat, b.fat, isAsc);
  //       default: return 0;
  //     }
  //   });
  // }
  //
  //
  //   compare(a: number | string, b: number | string, isAsc: boolean) {
  //     return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  //   }




}
