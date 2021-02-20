import {AfterViewInit, Component, Input, ViewChild} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Operation, User} from '../../models/models';
import {UserService} from '../../services/user-service';

@Component({
  selector: 'app-sort-table',
  templateUrl: './sort-table.component.html',
  styleUrls: ['./sort-table.component.css']
})
export class SortTableComponent implements AfterViewInit {


  // operationList: Operation[] = [{id: 1, description: "xdddddd", value: -20, date: "2021-05-01"},
  //   {id: 2, description: "dsc 2", value: 20, date: "2020-02-04"},
  //   {id: 2, description: "dsc 2", value: 20, date: "2021-01-10"},
  //   {id: 2, description: "dsc 2", value: 20, date: "2021-05-10"}];
  _operationList: Operation[];
  dataSource: MatTableDataSource<Operation>;
  displayedColumns: string[] = ['id', 'description', 'value', 'date', 'action'];
  userId: number;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getUser$()
      .subscribe((user: User) => {
        this.userId = user.id;
      });
  }

  @Input() set operationList(operationList: Operation[]) {
    this._operationList = operationList;
    this.dataSource = new MatTableDataSource(this._operationList);
  }

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  deleteOperation(operationId: number): void {
    this.userService.deleteOperation(this.userId, operationId);

    this.userService.reEmitUser();
    this.userService.flag.next(true);
  }
}
