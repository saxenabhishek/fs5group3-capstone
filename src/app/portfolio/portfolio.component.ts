import { Component, OnInit } from '@angular/core';
import { Trade } from '../models/trade';
import { TradeService } from '../services/trade/trade.service';
import { Prices } from '../models/prices';
import { Instruments } from '../models/instruments';
import { ClientService } from '../services/client/client.service';
import { ActivatedRoute } from '@angular/router';

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
  isLoading: boolean= true;

  constructor(private tradeService: TradeService, private clientService: ClientService, private route: ActivatedRoute) { 
    const resolvedData = this.route.snapshot.data['prices'];
    if (resolvedData) {
      this.prices = resolvedData;
      // Now you can call other functions that depend on prices
      // this.setPortfolioChartValues();
      // ...
    }
  }

  ngOnInit() {
    // this.loadAllPrices();
    this.loadAllTrades();
  }

  calcTotalHoldings(){
    // if (!this.prices) return;
    this.trades.forEach(trade => 
      this.totalHoldings+= (1 + 
        ((this.getInstrumentAskPrice(trade.instrumentId) - trade.executionPrice) / trade.executionPrice)) * trade.cashValue);
    console.log("HOLDINGS", this.totalHoldings)
  }

  calcTotalCashValue(){
    this.trades.forEach(trade => this.totalCashValue+=  trade.cashValue);
    console.log("CASHVALUE",this.totalCashValue);
  }
  
  loadAllTrades(){
    this.tradeService.getCurrentHoldings(this.clientService.verifyClient.clientId)
          .subscribe(allTrades => {
            this.trades= allTrades
            console.log("TRADES", this.trades)
            this.calcTotalCashValue();
            this.calcTotalHoldings();
            this.setPortfolioChartValues();
          });
  }

  // loadAllPrices(){
  //   this.tradeService.getCurrentPrices("")
  //         .subscribe(prices => {this.prices= prices; this.isLoading= false;});
  // }

  setPortfolioChartValues(){
    // if (this.prices.length == 0) return;
    this.trades.forEach(trade => this.portfolioLabels.push(this.getInstrumentName(trade.instrumentId)));
    this.trades.forEach(trade => this.portfolioData.push(((1 + 
                                  (this.getInstrumentAskPrice(trade.instrumentId) - trade.executionPrice) 
                                  / trade.executionPrice) * trade.cashValue)).toFixed(2));
    console.log(this.portfolioData, this.portfolioLabels)
  }

  getInstrumentName(id: string): any{
    // if (this.prices.length == 0) return;
    return this.prices.find(ins => ins.instrument.instrumentId === id)?.instrument.instrumentDescription;
  }

  getInstrumentCategory(id: string): any{
    // if (this.prices.length == 0) return;
    return this.prices.find(ins => ins.instrument.instrumentId === id)?.instrument.categoryId;
  }

  getInstrumentAskPrice(id: string): any{
    // if (this.prices.length == 0) return;
    return this.prices.find(ins => ins.instrument.instrumentId === id)?.askPrice;    
  }

  abs(num: number): number{
    return Math.abs(num)
  }
}