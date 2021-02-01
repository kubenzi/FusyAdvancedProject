import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user-service';
import {User} from '../../models/models';

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
    this.userService.setUser$().subscribe();
    this.user = this.userService.getUser();
  }
}
