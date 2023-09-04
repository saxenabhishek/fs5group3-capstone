import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

ActivatedRoute

import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexTitleSubtitle,
} from 'ng-apexcharts';

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  title: ApexTitleSubtitle;
};

@Component({
  selector: 'app-stock-page',
  templateUrl: './stock-page.component.html',
  styleUrls: ['./stock-page.component.css'],
})
export class StockPageComponent {
  @ViewChild('chart') chart?: ChartComponent
  public chartOptions: ChartOptions;
  instrumentId: string = ""

  constructor( private route: ActivatedRoute) {
    this.instrumentId = route.snapshot.params["id"]
    this.chartOptions = {
      series: [
        {
          name: 'My-series',
          data: [10, 41, 35, 51, 49, 62, 69, 91, 148],
        },
      ],
      chart: {
        height: 500,
        type: 'bar',
      },
      title: {
        text: 'price of ' + this.instrumentId,
      },
      xaxis: {
        categories: [
          'Jan',
          'Feb',
          'Mar',
          'Apr',
          'May',
          'Jun',
          'Jul',
          'Aug',
          'Sep',
        ],
      },
    };
  }
}
