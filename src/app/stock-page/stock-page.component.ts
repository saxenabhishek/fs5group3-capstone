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
import { Order } from '../models/order';
import { Direction } from '../models/direction';
import { ClientService } from '../services/client/client.service';

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
  public chartOptions: ChartOptions = {
    series: [],
    chart: { height: 0, type: 'line' },
    xaxis: {},
    title: {},
    noData: {},
  };
  instrumentId: string = '';
  instrument?: Instruments;
  prices?: Prices;
  buyForm: FormGroup = new FormGroup({});
  clientId: string= '';

  constructor(
    private route: ActivatedRoute,
    private tradeService: TradeService,
    private formBuilder: FormBuilder,
    private clientService: ClientService
  ) {}
  ngOnInit() {
    this.instrumentId = this.route.snapshot.params['id'];
    this.buyForm = this.formBuilder.group({
      price: [
        '',
        Validators.compose([Validators.required, Validators.minLength(3)]),
      ],
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
    this.tradeService.getCurrentPrices(this.instrumentId).subscribe((data) => {
      this.prices = data[0];
      this.instrument = data[0].instrument;
      this.buyForm.get('price')?.setValue(this.prices.askPrice);
      this.buyForm
        .get('quantity')
        ?.setValue(this.prices.instrument.minQuantity);
      this.buyForm
        .get('quantity')
        ?.addValidators(
          Validators.min(this.prices.instrument.minQuantity.valueOf())
        );
      console.debug(this.buyForm);
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

  submitTrade() {
    console.debug('submitTrade()');
    this.clientId = this.clientService.getClientId();
    let newOrder = new Order(
      'OID' + this.generateRandomString(3),
      this.instrumentId,
      this.buyForm.get('quantity')?.value,
      this.buyForm.get('price')?.value,
      new Direction('BUY'),
      this.clientId,
      this.getTimeString()
    );
    let processedOrder = {...newOrder, direction: newOrder.direction.stringValue}
    console.debug(processedOrder);
    this.tradeService.processOrder(processedOrder).subscribe({
      next: (data) => {
        console.log(data);
        alert('trade executed');
      },
      error: () => {
        alert('trade could not be executed');
      },
    });
  }

  isFormValid(): boolean {
    console.debug(this.buyForm.valid);
    return this.buyForm.valid;
  }

  generateRandomString(length: number): string {
    const characters = '0123456789';
    let result = '';

    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * characters.length);
      result += characters.charAt(randomIndex);
    }

    return result;
  }

  getTimeString() {
    const now = new Date();

    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0'); // Zero-padded month (01-12)
    const date = String(now.getDate()).padStart(2, '0'); // Zero-padded day (01-31)
    const hours = String(now.getHours()).padStart(2, '0'); // Zero-padded hours (00-23)
    const minutes = String(now.getMinutes()).padStart(2, '0'); // Zero-padded minutes (00-59)
    const seconds = String(now.getSeconds()).padStart(2, '0'); // Zero-padded seconds (00-59);
    const milliseconds = String(now.getMilliseconds()).padStart(3, '0'); // Milliseconds (000-999)
    
    const formattedDateTime = `${year}-${month}-${date}T${hours}:${minutes}:${seconds}.${milliseconds}Z`;
    

    return formattedDateTime;
  }
}
