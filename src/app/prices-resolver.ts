import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { Observable } from 'rxjs';
import { TradeService } from './services/trade/trade.service';

@Injectable({
  providedIn: 'root',
})
export class PricesResolver implements Resolve<any[]> {
  constructor(private tradeService: TradeService) {}

  resolve(): Observable<any[]> {
    // Fetch prices data and return it as an observable
    return this.tradeService.getCurrentPrices("");
  }
}
