import { Component, OnInit } from '@angular/core';
import { Trade } from '../models/trade';
import { TradeService } from '../services/trade.service';
import { Prices } from '../models/prices';
import { Instruments } from '../models/instruments';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit{
  trades: Trade[]= [];
  totalHoldings: number= 0;
  totalCashValue: number= 0;
  prices: Prices[]= [];
  instruments: Instruments[]= [];
  portfolioData: number[] = []; 
  portfolioLabels: any[] = []; 

  constructor(private tradeService: TradeService) { }

  ngOnInit() {
    this.loadAllInstruments();
    this.loadAllPrices();
    this.loadAllTrades();
    this.calcTotalCashValue();
    this.setPortfolioChartValues();
    this.calcTotalHoldings();
  }

  calcTotalHoldings(){
    this.trades.forEach(trade => 
      this.totalHoldings+= (1 + 
        ((this.getInstrumentPrice(trade.instrumentId) - trade.executionPrice) / trade.executionPrice)) * trade.cashValue);
  }

  calcTotalCashValue(){
    this.trades.forEach(trade => this.totalCashValue+=  trade.cashValue);
  }
  
  loadAllTrades(){
    this.tradeService.getTrades()
          .subscribe(trades => this.trades= trades);
  }

  loadAllInstruments(){
    this.tradeService.getInstruments()
          .subscribe(ins => this.instruments= ins);
  }

  loadAllPrices(){
    this.tradeService.getPrices()
          .subscribe(prices => this.prices= prices);
  }

  setPortfolioChartValues(){
    this.trades.forEach(trade => this.portfolioLabels.push(this.getInstrumentName(trade.instrumentId)));
    this.trades.forEach(trade => this.portfolioData.push(((1 + 
                                  (this.getInstrumentPrice(trade.instrumentId) - trade.executionPrice) 
                                  / trade.executionPrice) * trade.cashValue)).toFixed(2));
  }

  getInstrumentName(id: string): any{
    return this.instruments.find(ins => ins.instrumentId === id)?.instrumentDescription;
  }

  getInstrumentCategory(id: string): any{
    return this.instruments.find(ins => ins.instrumentId === id)?.categoryId;
  }

  getInstrumentPrice(id: string): any{
    return this.prices.find(ins => ins.instrument.instrumentId === id)?.askPrice;
  }

  abs(num: number): number{
    return Math.abs(num)
  }
}