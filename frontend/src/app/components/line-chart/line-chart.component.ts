import {Component, Input, OnInit} from '@angular/core';
import {Data} from '../../models/models';
import {UserService} from '../../services/user-service';

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css']
})
export class LineChartComponent implements OnInit {

  lineChartData: Data[];

  view: any[] = [800, 300];

  // options
  legend = true;
  // showLabels = true;
  // animations = true;
  xAxis = true;
  yAxis = true;
  showYAxisLabel = true;
  showXAxisLabel = true;
  xAxisLabel = 'Date';
  yAxisLabel = 'Total Balance';
  timeline = true;


  // showGridLines: true;
  // rangeFillOpacity = 0;
  // curve: any;

  colorScheme = {
    domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  };

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    // this.userService.getLineChartDataForInit30().subscribe(value => this.lineChartData = value);
    this.userService.getLineChartDataForPeriod(
      this.userService.address, 30
    ).subscribe(value => this.lineChartData = value);
  }

  setPeriod(period: number): void {
    this.lineChartData = [];
    this.userService.getLineChartDataForPeriod(
      this.userService.address, period).subscribe(value => this.lineChartData = [...value]);
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

}
