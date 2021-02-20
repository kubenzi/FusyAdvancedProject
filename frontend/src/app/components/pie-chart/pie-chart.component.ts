import {Component, OnInit} from '@angular/core';
import {Series, User} from '../../models/models';
import {UserService} from '../../services/user-service';
import {Router} from '@angular/router';
import {take} from 'rxjs/operators';


@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent implements OnInit {

  userId: number;
  pieChartData: Series[] = [];
  period = 30;

  view: any[] = [400, 300];

  // options
  gradient = true;
  showLegend = true;
  showLabels = true;
  isDoughnut = true;

  legendPosition = 'below';
  explodeSlices: true;

  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
  };

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getUser$()
      .subscribe((user: User) => {
        this.userId = user.id;
      });
    this.userService.getAddressChange$().subscribe(() => {
      this.userService.getPieChartDataStartForPeriod(this.userService.address, 30).subscribe(
        value => this.pieChartData = value);
    });
    this.userService.getPieChartDataStart().subscribe(value => this.pieChartData = value);
  }

  onSelect(data): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

  setPeriod(period: number): void {
    this.pieChartData = [];
    this.period = period;
    this.userService.getPieChartDataStartForPeriod(
      this.userService.address, this.period).subscribe(
      value => this.pieChartData = value);
  }
}
