import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, throwError } from 'rxjs';
import { INSTRUMENT_DATA } from 'src/app/const/instrument';
import { Instruments } from 'src/app/models/instruments';
import { Order } from 'src/app/models/order';
import { Prices } from 'src/app/models/prices';
import { Trade } from 'src/app/models/trade';
import { ClientService } from '../client/client.service';

@Injectable({
  providedIn: 'root',
})
export class TradeService {
  trades: Trade[] = [];
  url = 'http://localhost:8080/trade';
  portfolioUrl= 'http://localhost:8080/portfolio';

  constructor(private http: HttpClient, private clientService: ClientService) {}

  processOrder(order: Order) {
    // todo: make call to backend to process an order and make a trade
    // this.order_data.push(order);

    // const existingTradeIndex = this.trade_data.findIndex(
    //   (trade) => trade.instrumentId === order.instrumentId
    // );
    
    // if (existingTradeIndex !== -1) {
    //   // An existing trade with the same instrumentId was found
    //   // Update the existing trade's data
    //   if (order.direction.stringValue == "B"){        
    //     this.trade_data[existingTradeIndex].cashValue= parseInt(this.trade_data[existingTradeIndex].cashValue.toString()) + (parseInt(order.targetPrice.toString()) * parseInt(order.quantity.toString()));
    //     this.trade_data[existingTradeIndex].quantity = parseInt(this.trade_data[existingTradeIndex].quantity.toString()) + parseInt(order.quantity.toString());
    //     console.log(typeof this.trade_data[existingTradeIndex].quantity)
    //   }
    //   // else if (order.direction.stringValue == "S"){        
    //   //   this.trade_data[existingTradeIndex].cashValue-= order.targetPrice - 12;
    //   //   this.trade_data[existingTradeIndex].quantity-= order.quantity;
    //   //   this.trade_data[existingTradeIndex].executionPrice-= order.targetPrice - 3;
    //   // }
    // } 
    // else {
    //   // No existing trade with the same instrumentId was found, so create a new one
    //   this.trade_data.push(
    //     new Trade(
    //       order,
    //       order.targetPrice * order.quantity,
    //       // order.targetPrice + 12,
    //       order.quantity,
    //       order.direction.BUY,
    //       order.targetPrice + 3,
    //       order.instrumentId,
    //       order.clientId,
    //       this.trade_data.length + 1 + '23423',
    //       ''
    //     )
    //   );
    // }
  }

  getCurrentHoldings(clientId: string): Observable<Trade[]> {
    return this.http.get<Trade[]>(this.portfolioUrl + '/holdings?clientId=' + clientId)
                    .pipe(
                      catchError((error) => {
                        console.error('API error for GET Holdings:', error);
                        return throwError(() => error);
                      }));
  }

  getInstruments(): Observable<Instruments[]> {
    return this.http.get<Instruments[]>(this.url + "/instruments")
    // return of(INSTRUMENT_DATA)
  }

  getCurrentPrices(instrumentId: string): Observable<Prices[]> {
    let modURL;
    if (instrumentId != "")
      modURL= this.portfolioUrl +'/prices?instrumentId=' + instrumentId;
    else
      modURL= this.portfolioUrl +'/prices';
    
    return this.http.get<Prices[]>(modURL)
                    .pipe(
                      catchError((error) => {
                        console.error('API error for GET Current Prices:', error);
                        return throwError(() => error);
                      }));
  }

  getTradeHistory(clientId: string): Observable<any[]> {    
    return this.http.get<any[]>(this.portfolioUrl + '/trade-history?clientId=' + clientId)
                    .pipe(
                      catchError((error) => {
                        console.error('API error for GET Trade History:', error);
                        return throwError(() => error);
                      }));
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
}
