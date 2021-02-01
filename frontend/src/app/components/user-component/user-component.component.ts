import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {User} from '../../models/models';
import {AuthService} from '../../authentication/services/auth.service';

@Component({
  selector: 'app-user-component',
  templateUrl: './user-component.component.html',
  styleUrls: ['./user-component.component.css']
})
export class UserComponentComponent implements OnInit {

  user: User;
  totalBalance = 0;

  constructor(private userService: UserService, public authService: AuthService) { }

  ngOnInit(): void {
    this.user = this.userService.getUser();
  }
}
