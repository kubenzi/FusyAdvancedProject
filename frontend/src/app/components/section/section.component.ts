import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  firstName: string;
  lastName: string;
  email: string;

  constructor(private router: Router) {
    this.firstName = this.router.getCurrentNavigation().extras.state.firstName;
    this.lastName = this.router.getCurrentNavigation().extras.state.lastName;
    this.email = this.router.getCurrentNavigation().extras.state.email;
  }

  ngOnInit(): void {
  }

}
