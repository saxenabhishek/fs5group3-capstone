import { Component, OnInit } from '@angular/core';
import { Trade } from '../models/trade';
import { TradeService } from '../services/trade/trade.service';
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

  async calcTotalHoldings(){
    await this.trades.forEach(trade => 
      this.totalHoldings+= (1 + 
        ((this.getInstrumentAskPrice(trade.instrumentId) - trade.executionPrice) / trade.executionPrice)) * trade.cashValue);
  }

  async calcTotalCashValue(){
    await this.trades.forEach(trade => this.totalCashValue+=  trade.cashValue);
    console.log(this.trades);
  }
  
  loadAllTrades(){
    this.tradeService.getCurrentHoldings("UID001")
          .subscribe(allTrades => this.trades= allTrades);
  }

  loadAllInstruments(){
    this.tradeService.getInstruments()
          .subscribe(ins => this.instruments= ins);
  }

  loadAllPrices(){
    this.tradeService.getCurrentPrices("")
          .subscribe(prices => this.prices= prices);
  }

  setPortfolioChartValues(){
    this.trades.forEach(trade => this.portfolioLabels.push(this.getInstrumentName(trade.instrumentId)));
    this.trades.forEach(trade => this.portfolioData.push(((1 + 
                                  (this.getInstrumentAskPrice(trade.instrumentId) - trade.executionPrice) 
                                  / trade.executionPrice) * trade.cashValue)).toFixed(2));
  }

  getInstrumentName(id: string): any{
    return this.instruments.find(ins => ins.instrumentId === id)?.instrumentDescription;
  }

  getInstrumentCategory(id: string): any{
    return this.instruments.find(ins => ins.instrumentId === id)?.categoryId;
  }

  getInstrumentAskPrice(id: string): any{
    console.log(this.trades);

    return this.prices.find(ins => ins.instrument.instrumentId === id)?.askPrice;
    
  }

  abs(num: number): number{
    return Math.abs(num)
  }
}