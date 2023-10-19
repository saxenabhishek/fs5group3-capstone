import { Injectable } from '@angular/core';
import { MockReport } from '../../models/mock-report';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Order } from 'src/app/models/order';
import { BASEURL } from 'src/app/const/enviorment';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {
  private apiUrl = BASEURL + '/client';

  constructor(private http: HttpClient) { }

//   reports:MockReport[]= [{
//     tradePrice: 12309,
//     tradeType: 'Mutual Fund',
//     taxInfo: 'VAT',
//     currency: 500,
//     tradeDate:'12/07/2021',
//     confirmationDate:'13/07/2021'
// }, {
//   tradePrice: 758659,
//   tradeType: 'ETF',
//   taxInfo: 'General tax',
//   currency: 800,
//   tradeDate:'12/07/2021',
//   confirmationDate:'13/07/2021'
// },
// {
//   tradePrice: 7683951,
//   tradeType: 'Mutual Fund',
//   taxInfo: 'VAT',
//   currency: 500,
//   tradeDate:'12/07/2021',
//   confirmationDate:'13/07/2021'
// }

// ];
// getReport(): Observable<MockReport[]>
// {
// return of(this.reports);
// }
  getReport(clientId: string, direction: string): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}/activity-report/${clientId}/${direction}`);
  }
// /api/reports/generate

}

