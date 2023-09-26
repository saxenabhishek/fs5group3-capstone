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
  order_data = ORDER_DATA;
  trade_data = TRADE_DATA;

  constructor(private http: HttpClient) {}

  processOrder(order: Order) {
    // todo: make call to backend to process an order and make a trade
    this.order_data.push(order);

    const existingTradeIndex = this.trade_data.findIndex(
      (trade) => trade.instrumentId === order.instrumentId
    );
    
    if (existingTradeIndex !== -1) {
      // An existing trade with the same instrumentId was found
      // Update the existing trade's data
      if (order.direction.stringValue == "B"){        
        this.trade_data[existingTradeIndex].cashValue= parseInt(this.trade_data[existingTradeIndex].cashValue.toString()) + (parseInt(order.targetPrice.toString()) * parseInt(order.quantity.toString()));
        this.trade_data[existingTradeIndex].quantity = parseInt(this.trade_data[existingTradeIndex].quantity.toString()) + parseInt(order.quantity.toString());
        console.log(typeof this.trade_data[existingTradeIndex].quantity)
      }
      // else if (order.direction.stringValue == "S"){        
      //   this.trade_data[existingTradeIndex].cashValue-= order.targetPrice - 12;
      //   this.trade_data[existingTradeIndex].quantity-= order.quantity;
      //   this.trade_data[existingTradeIndex].executionPrice-= order.targetPrice - 3;
      // }
    } 
    else {
      // No existing trade with the same instrumentId was found, so create a new one
      this.trade_data.push(
        new Trade(
          order,
          order.targetPrice * order.quantity,
          // order.targetPrice + 12,
          order.quantity,
          order.direction,
          order.targetPrice + 3,
          order.instrumentId,
          order.clientId,
          this.trade_data.length + 1 + '23423'
        )
      );
    }
  }

  getTrades(): Observable<Trade[]> {
    return of(this.trade_data);
  }

  getInstruments(): Observable<Instruments[]> {
    // return this.http.get<Instruments[]>(this.url)
    return of(INSTRUMENT_DATA);
  }

  getPrices(): Observable<Prices[]> {
    return of(PRICES);
  }

  getOrders(): Observable<Order[]> {
    return of(this.order_data);
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
