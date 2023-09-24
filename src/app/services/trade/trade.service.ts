import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { INSTRUMENT_DATA } from 'src/app/const/instrument';
import { ORDER_DATA } from 'src/app/const/order';
import { PRICES } from 'src/app/const/prices';
import { TRADE_DATA } from 'src/app/const/trade';
import { Instruments } from 'src/app/models/instruments';
import { Order } from 'src/app/models/order';
import { Prices } from 'src/app/models/prices';
import { Trade } from 'src/app/models/trade';

@Injectable({
  providedIn: 'root',
})
export class TradeService {
  trades: Trade[] = [];
  url = 'http://localhost:3000/fmts/trades/instruments';

  constructor(private http: HttpClient) {}

  getTrades(): Observable<Trade[]> {
    return of(TRADE_DATA);
  }

  getInstruments(): Observable<Instruments[]> {
    // return this.http.get<Instruments[]>(this.url)
    return of(INSTRUMENT_DATA);
  }

  getPrices(): Observable<Prices[]> {
    return of(PRICES);
  }

  getOrders(): Observable<Order[]> {
    return of(ORDER_DATA);
  }

  getInstrumentsById(id: String): Observable<Instruments> {
    let ele: Instruments = new Instruments('', '', '', '', '', 0, 0);
    INSTRUMENT_DATA.forEach((element) => {
      if (element.instrumentId == id) {
        ele = element;
      }
    });
    return of(ele);
  }

  getPriceById(id: String): Observable<Prices> {
    let ele: Prices = new Prices(
      0,
      0,
      '',
      new Instruments('', '', '', '', '', 0, 0)
    );
    PRICES.forEach((element) => {
      if (element.instrument.instrumentId == id) {
        ele = element;
      }
    });
    return of(ele);
  }
}
