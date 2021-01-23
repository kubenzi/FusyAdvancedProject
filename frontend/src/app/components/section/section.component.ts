import {Component, OnInit} from '@angular/core';
import {User, UserService} from '../../services/user-service';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  user: User;

  constructor(private userService: UserService) {
    // this.firstName = this.router.getCurrentNavigation().extras.state.firstName;
    // this.lastName = this.router.getCurrentNavigation().extras.state.lastName;
    // this.email = this.router.getCurrentNavigation().extras.state.email;
  }

  ngOnInit(): void {
    this.user = this.userService.getUser();
  }

}
