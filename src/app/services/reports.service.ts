import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { MockReport } from '../models/mock-report';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {
  reports:MockReport[]= [{
    tradePrice: 12309,
    tradeType: 'Mutual Fund',
    taxInfo: 'VAT',
    currency: 500,
    tradeDate:'12/07/2021',
    confirmationDate:'13/07/2021'
}, {
  tradePrice: 758659,
  tradeType: 'ETF',
  taxInfo: 'General tax',
  currency: 800,
  tradeDate:'12/07/2021',
  confirmationDate:'13/07/2021'
},
{
  tradePrice: 7683951,
  tradeType: 'Mutual Fund',
  taxInfo: 'VAT',
  currency: 500,
  tradeDate:'12/07/2021',
  confirmationDate:'13/07/2021'
}

];
getReport(): Observable<MockReport[]>
{
return of(this.reports);
}

  constructor() { }
}

