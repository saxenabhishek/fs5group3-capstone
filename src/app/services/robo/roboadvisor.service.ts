import { Injectable } from '@angular/core';
import { MockRoboadvisor } from '../../models/mock-roboadvisor';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Trade } from 'src/app/models/trade';

@Injectable({
  providedIn: 'root'
})
export class RoboadvisorService {
private apiUrl="http://localhost:8080/trade";

  roboadvisor:MockRoboadvisor[]= [{
    securityName:'FidelityMF',
    stockSymbol:'FMF',
    tradeId: 12,
    tradePrice:50000,
    tradeType: 'Mutual Fund',
    tradeFluctuation:20,
    buy:'BUY' ,
    sell:'SELL' 
}, {
  securityName:'iShares India',
  stockSymbol:'INDV',
  tradeId: 22,
  tradePrice:60000,
  tradeType: 'ETF',
  tradeFluctuation:90,
  buy:'BUY' ,
  sell:'SELL'
},
{
  securityName:'Apple',
  stockSymbol:'AAPL',
  tradeId: 12,
  tradePrice:50000,
  tradeType: 'Stock',
  tradeFluctuation:50,
  buy:'BUY' ,
  sell:'SELL'
},
{
  securityName:'Microsoft',
  stockSymbol:'MSFT',
  tradeId: 12,
  tradePrice:50000,
  tradeType: 'Stock',
  tradeFluctuation:90, 
  buy:'BUY' ,
  sell:'SELL' 
},
{
  securityName:'Google',
  stockSymbol:'GOOG',
  tradeId: 12,
  tradePrice:50000,
  tradeType: 'Stock',
  tradeFluctuation:60, 
  buy:'BUY' ,
  sell:'SELL' 
}

];
// getData(): Observable<MockRoboadvisor[]>
// {
// return of(this.roboadvisor);
// }
getData(): Observable<Trade[]> {
  return this.http.get<Trade[]>(`${this.apiUrl}/robo-advisor/UID001`);
}
constructor(private http: HttpClient) { }
}
