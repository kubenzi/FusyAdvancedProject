import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {Data, Series, User} from '../../models/models';
import {Observable} from 'rxjs';
import {MatIconModule} from '@angular/material/icon';
import {ActivatedRoute, ActivationStart, NavigationStart, Router} from '@angular/router';
import {first} from 'rxjs/operators';
import {AuthService} from '../../authentication/services/auth.service';
import {MatDialog} from '@angular/material/dialog';
import {DialogFormComponent} from '../dialog-form/dialog-form.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],

})
export class MenuComponent implements OnInit {

  user$: Observable<User>;
  list: String[] = ["blue", "red", "gray", "black"];
  pieChartData: Series[] = [];

  animal: string;
  name: string;

  constructor(private userService: UserService, public dialog: MatDialog) {

  }

  ngOnInit(): void {
    this.user$ = this.userService.getUser$();
    this.userService.reEmitUser();

  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogFormComponent, {
      width: '250px',
      data: {name: this.name, animal: this.animal}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.userService.reEmitUser();
      this.userService.flag.next(true);
    });
  }

}
