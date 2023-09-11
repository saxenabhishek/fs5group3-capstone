import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-portfolio-chart',
  templateUrl: './portfolio-chart.component.html',
  styleUrls: ['./portfolio-chart.component.css']
})
export class PortfolioChartComponent {
  @Input() 
  chartData: any[] = [];
  @Input() 
  chartLabels: string[] = [];
}
