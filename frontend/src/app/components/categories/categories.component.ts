import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {Category, Operation, User} from '../../models/models';
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../services/user-service";
import {filter, switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  user$: Observable<User>;
  category: Observable<Category>;
  categoryId: number;
  operations: Operation[] = [{id: 1, description: "dsc", value: -20, date: "2020-20-01"},
    {id: 2, description: "dsc 2", value: 20, date: "2020-20-04"}];

  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    this.categoryId = Number(this.route.snapshot.paramMap.get('id'));
    this.user$ = this.userService.getUser$();
    this.category = this.userService.getUser$().pipe(
      filter(user => !!user),
      switchMap(
        user$ => this.userService.getCategoryInfo(user$.id, this.categoryId)
      )
    )
    this.category.subscribe(value => console.log(value))
  }
}
