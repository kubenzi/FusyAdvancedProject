import {Component, OnInit} from '@angular/core';
import {User, UserService} from '../../services/user-service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  user: User;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getUser()
      .subscribe(value => this.user = value);
  }

}
