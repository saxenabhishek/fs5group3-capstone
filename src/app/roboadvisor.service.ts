import { Injectable } from '@angular/core';
import { MockRoboadvisor } from './models/mock-roboadvisor';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoboadvisorService {
  roboadvisor:MockRoboadvisor[]= [{
    securityName:'FidelityMF',
    stockSymbol:'FMF',
    tradeId: 12,
    tradePrice:50000,
    tradeType: 'Mutual Fund',
    tradeQuantity:20,
    tradeDate:'12/07/2021', 
    buy:'BUY' ,
    sell:'SELL' 
}, {
  securityName:'iShares India',
  stockSymbol:'INDV',
  tradeId: 22,
  tradePrice:60000,
  tradeType: 'ETF',
  tradeQuantity:90,
  tradeDate:'12/07/2021',
  buy:'BUY' ,
  sell:'SELL'
},
{
  securityName:'Apple',
  stockSymbol:'AAPL',
  tradeId: 12,
  tradePrice:50000,
  tradeType: 'Stock',
  tradeQuantity:50,
  tradeDate:'12/07/2021',
  buy:'BUY' ,
  sell:'SELL'
}

];
getData(): Observable<MockRoboadvisor[]>
{
return of(this.roboadvisor);
}

  constructor() { }
}
