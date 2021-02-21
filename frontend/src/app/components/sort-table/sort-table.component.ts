import {AfterViewInit, Component, Input, ViewChild} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Category, Operation, User} from '../../models/models';
import {UserService} from '../../services/user-service';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-sort-table',
  templateUrl: './sort-table.component.html',
  styleUrls: ['./sort-table.component.css']
})
export class SortTableComponent implements AfterViewInit {

  _operationList: Operation[];
  dataSource: MatTableDataSource<Operation>;
  displayedColumns: string[] = ['id', 'description', 'value', 'date', 'category-change', 'action'];
  userId: number;


  changeCategoryFormGroup: FormGroup;
  id = new FormControl('');
  name = new FormControl('');

  constructor(private userService: UserService,
              private formBuilder: FormBuilder) {
  }


  ngOnInit(): void {
    this.userService.getUser$()
      .subscribe((user: User) => {
        this.userId = user.id;
        });
    this.changeCategoryFormGroup = this.formBuilder.group({
      name: this.name,
      id: this.id
      })
  }

  @Input() categoryList: Category[];

  @Input() set operationList(operationList: Operation[]) {
    this._operationList = operationList;
    this.dataSource = new MatTableDataSource(this._operationList);
  }

  get form() { return this.changeCategoryFormGroup.controls; }

  @ViewChild(MatSort) sort: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  deleteOperation(operationId: number): void {
    this.userService.deleteOperation(this.userId, operationId);

    this.userService.reEmitUser();
    this.userService.flag.next(true);
  }

  updateOperationCategory(operationId: number) {
    console.log(operationId);
    this.userService.updateOperationCategory(
      {
        categoryId: this.form.categoryId.value
      }, operationId
    )
      .subscribe((operation) => {

        console.log('operation updated')
      })
  }
}
