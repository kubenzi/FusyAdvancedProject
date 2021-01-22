import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../../services/user-service';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  @Input() firstName: string;
  @Input() lastName: string;
  @Input() user: User;

  constructor() {
    // this.firstName = this.router.getCurrentNavigation().extras.state.firstName;
    // this.lastName = this.router.getCurrentNavigation().extras.state.lastName;
    // this.email = this.router.getCurrentNavigation().extras.state.email;
  }

  ngOnInit(): void {
  }

}
