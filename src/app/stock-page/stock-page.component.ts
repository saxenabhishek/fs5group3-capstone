import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

ActivatedRoute;

import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexTitleSubtitle,
  ApexNoData,
} from 'ng-apexcharts';
import { TradeService } from '../services/trade/trade.service';
import { Instruments } from '../models/instruments';
import { Prices } from '../models/prices';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  title: ApexTitleSubtitle;
  noData: ApexNoData;
};

@Component({
  selector: 'app-stock-page',
  templateUrl: './stock-page.component.html',
  styleUrls: ['./stock-page.component.css'],
})
export class StockPageComponent {
  @ViewChild('chart') chart?: ChartComponent;
  public chartOptions: ChartOptions;
  instrumentId: string = '';
  instrument?: Instruments;
  prices?: Prices;
  buyForm: FormGroup = new FormGroup({});

  constructor(
    private route: ActivatedRoute,
    private tradeService: TradeService,
    private formBuilder: FormBuilder
  ) {
    this.instrumentId = route.snapshot.params['id'];
    this.buyForm = this.formBuilder.group({
      price: ['', Validators.compose([Validators.required, Validators.min(3)])],
      quantity: ['1', Validators.required],
    });
    this.chartOptions = {
      series: [],
      chart: {
        height: 500,
        type: 'line',
      },
      title: {
        text: this.instrumentId,
      },
      xaxis: {
        type: 'datetime',
      },
      noData: {
        text: 'Loading...',
      },
    };
    tradeService.getPriceById(this.instrumentId).subscribe((data) => {
      this.prices = data;
      this.instrument = data.instrument;
      this.buyForm.get("price")?.setValue(this.prices.askPrice)
      this.chartOptions.series = [
        {
          // data: [10, 41, 35, 51, 49, 62, 69, 91, 148].map((e, i) => {
          data: [...Array(20)].map((e, i) => {
            return [
              new Date().getTime() + i * 1800000,
              Math.round(Math.random() * 100) + (this.prices?.askPrice || 1000),
            ];
          }),
        },
      ];
    });
  }

  submitTrade(){
    alert("Trade Executed")
  }
}
