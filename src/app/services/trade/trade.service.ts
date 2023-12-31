import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, throwError } from 'rxjs';
import { INSTRUMENT_DATA } from 'src/app/const/instrument';
import { Instruments } from 'src/app/models/instruments';
import { Order } from 'src/app/models/order';
import { Prices } from 'src/app/models/prices';
import { Trade } from 'src/app/models/trade';
import { ClientService } from '../client/client.service';
import { Wallet } from 'src/app/models/wallet.spec';
import { BASEURL } from 'src/app/const/enviorment';

@Injectable({
  providedIn: 'root',
})
export class TradeService {
  trades: Trade[] = [];
  url =  BASEURL + "/trade";
  portfolioUrl = BASEURL  + "/portfolio"

  constructor(private http: HttpClient, private clientService: ClientService) {}

  processOrder(order: {
    orderId: string;
    instrumentId: string;
    quantity: number;
    targetPrice: number;
    direction: string;
    clientId: string;
    orderTimestamp: string;
  }) {
    return this.http.post(this.url + '/make-trade', order);
  }

  getCurrentHoldings(clientId: string): Observable<Trade[]> {
    return this.http
      .get<Trade[]>(`${this.portfolioUrl}/holdings?clientId=${clientId}`)
      .pipe(
        catchError((error) => {
          console.error('API error for GET Holdings:', error);
          return throwError(() => error);
        })
      );
  }

  getInstruments(): Observable<Instruments[]> {
    return this.http.get<Instruments[]>(this.url + '/instruments');
  }

  getCurrentPrices(instrumentId: string): Observable<Prices[]> {
    let modURL;
    if (instrumentId != '')
      modURL = `${this.portfolioUrl}/prices?instrumentId=${instrumentId}`;
    else 
      modURL = `${this.portfolioUrl}/prices`;

    return this.http.get<Prices[]>(modURL).pipe(
      catchError((error) => {
        console.error('API error for GET Current Prices:', error);
        return throwError(() => error);
      })
    );
  }

  getWalletBalance(clientId: string): Observable<Wallet>{
    return this.http
      .get<Wallet>(`${this.portfolioUrl}/holdings/wallet?clientId=${clientId}`)
      .pipe(
        catchError((error) => {
          console.error('API error for GET Wallet Balance:', error);
          return throwError(() => error);
        })
      );
  }

  getTradeHistory(clientId: string): Observable<any[]> {
    return this.http
      .get<any[]>(`${this.portfolioUrl}/trade-history?clientId=${clientId}`)
      .pipe(
        catchError((error) => {
          console.error('API error for GET Trade History:', error);
          return throwError(() => error);
        })
      );
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
