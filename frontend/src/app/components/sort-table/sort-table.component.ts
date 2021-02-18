import {Component, Input, OnInit} from '@angular/core';

import {AfterViewInit, ViewChild} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Operation} from '../../models/models';

@Component({
  selector: 'app-sort-table',
  templateUrl: './sort-table.component.html',
  styleUrls: ['./sort-table.component.css']
})
export class SortTableComponent implements AfterViewInit{


  operationList: Operation[] = [{id: 1, description: "dsc 1", value: -20, date: "2021-05-01"},
    {id: 2, description: "dsc 2", value: 20, date: "2020-02-04"},
    {id: 2, description: "dsc 2", value: 20, date: "2021-01-10"},
    {id: 2, description: "dsc 2", value: 20, date: "2021-05-10"}];
  // @Input() operationList: Operation[] = [];

  displayedColumns: string[] = ['id', 'description', 'value', 'date'];
  dataSource = new MatTableDataSource(this.operationList);

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

}
