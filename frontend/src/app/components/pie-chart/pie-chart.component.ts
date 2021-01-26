import {Component, Input} from '@angular/core';
import {Series} from '../../models/models';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent {

  @Input() pieChartData: Series[];

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
