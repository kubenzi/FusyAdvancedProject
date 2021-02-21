import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../services/user-service";
import {filter, switchMap} from 'rxjs/operators';
import {Category, Operation, User} from '../../models/models';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  user$: Observable<User>;
  category: Observable<Category>;
  categoryId: number;
  categoryList: Category[] = [];

  constructor(private route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit(): void {
    this.categoryId = Number(this.route.snapshot.paramMap.get('id'));
    this.user$ = this.userService.getUser$();
    this.category = this.userService.getUser$().pipe(
      filter(user => !!user),
      switchMap(
        user$ => this.userService.getCategoryInfo(user$.id, this.categoryId)
      )
    )
    this.user$.subscribe(value => this.categoryList = value.categories);
  }
}
