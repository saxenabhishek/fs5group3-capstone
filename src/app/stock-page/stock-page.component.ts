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
  ApexDataLabels,
  ApexStroke,
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
  x_axis: ApexXAxis;
  dataLabels: ApexDataLabels;
  title: ApexTitleSubtitle;
  noData: ApexNoData;
  stroke: ApexStroke;
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
    x_axis: {},
    title: {},
    noData: {},
    dataLabels: {},
    stroke: {},
  };
  instrumentId: string = '';
  instrument?: Instruments;
  prices?: Prices;
  buyForm: FormGroup = new FormGroup({});
  clientId: string = '';

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
      dataLabels: {
        enabled: false,
      },
      stroke: {
        curve: 'smooth',
      },
      chart: {
        height: 500,
        type: 'line',
      },
      title: {
        text: this.instrumentId,
      },
      x_axis: {
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

      const askPrice = this.prices?.askPrice || 1000;
      const priceRange = 0.1; // 10% price range

      const minPrice = askPrice - askPrice * priceRange;
      const maxPrice = askPrice + askPrice * priceRange;

      const randomPriceList = [...Array(40)].map((e, i) => {
        const randomPrice = Math.random() * (maxPrice - minPrice) + minPrice;
        return [
          new Date().getTime() - i * 180000000,
          this.setPrecision(randomPrice, 2),
        ];
      });

      this.chartOptions.series = [
        {
          data: [ [new Date().getTime(), askPrice] ,...randomPriceList,],
        },
      ];
      // this.chartOptions.series[0].data.push([new Date().getTime() , askPrice || null)
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
    let processedOrder = {
      ...newOrder,
      direction: newOrder.direction.stringValue,
    };
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

  setPrecision(number: number, precision: number): number {
    const multiplier = Math.pow(10, precision);
    const roundedNumber = Math.round(number * multiplier) / multiplier;
    return roundedNumber;
  }

  getTotalPrice(){
    return this.buyForm.get("price")?.value*this.buyForm.get("quantity")?.value
  }

  getExecPrice() {
    return this.getTotalPrice()*1.05
  }
}
