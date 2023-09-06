import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Trade } from '../models/trade';

@Injectable({
  providedIn: 'root'
})
export class TradeService {
  trades: Trade[]= [];

  constructor() { }

  getAllTrades(): Trade[]{
    return this.trades;
  }
}
