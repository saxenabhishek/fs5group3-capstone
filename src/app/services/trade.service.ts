import { Injectable } from '@angular/core';
import { Observable, of, retry } from 'rxjs';
import { Trade } from '../models/trade';
import { HttpClient } from '@angular/common/http';
import { Instruments } from '../models/instruments';
import { INSTRUMENT_DATA } from '../const/instrument';
import { Prices } from '../models/prices';
import { PRICES } from '../const/prices';
import { TRADE_DATA } from '../const/trade';
import { Order } from '../models/order';
import { ORDER_DATA } from '../const/order';

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
    let ele: Prices = new Prices(0, 0, '', new Instruments('', '', '', '', '', 0, 0))
    PRICES.forEach((element) => {
      if (element.instrument.instrumentId == id) {
        ele = element
      }
    });
    return of(ele);
  }
  
}
