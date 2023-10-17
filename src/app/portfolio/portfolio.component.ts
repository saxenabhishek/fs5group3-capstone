import { Component, OnInit } from '@angular/core';
import { Trade } from '../models/trade';
import { TradeService } from '../services/trade/trade.service';
import { Prices } from '../models/prices';
import { Instruments } from '../models/instruments';
import { ClientService } from '../services/client/client.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit{
  trades: Trade[]= [];
  totalHoldings: number= 0;
  walletBalance: number= 0;
  prices: Prices[]= [];
  instruments: Instruments[]= [];
  portfolioData: number[] = []; 
  portfolioLabels: string[] = [];

  constructor(private tradeService: TradeService, private clientService: ClientService, private route: ActivatedRoute, private location: Router) { 
    const resolvedData = this.route.snapshot.data['prices'];
    if (resolvedData) 
      this.prices = resolvedData;
  }

  ngOnInit() {
    this.loadAllTrades();
  }

  calcTotalHoldings(){
    if (this.trades != null && this.trades.length > 0)
      this.trades.forEach(trade => 
        this.totalHoldings+= (this.getInstrumentBidPrice(trade.instrumentId) / this.getInstrumentAskPrice(trade.instrumentId)) * trade.cashValue
      );
  }
  
  loadAllTrades(){
    this.tradeService.getCurrentHoldings(this.clientService.verifyClient.clientId)
        .subscribe(allTrades => {
          this.trades= allTrades.filter(e => e.quantity !== 0);
          this.getWalletBalance();
          this.calcTotalHoldings();
          this.setPortfolioChartValues();
        });
  }

  setPortfolioChartValues(){
    if (this.trades != null && this.trades.length > 0){
      this.trades.forEach(trade => this.portfolioLabels.push(this.getInstrumentName(trade.instrumentId)));
      this.trades.forEach(trade => this.portfolioData.push((this.getInstrumentBidPrice(trade.instrumentId) / this.getInstrumentAskPrice(trade.instrumentId)) * trade.cashValue));
      this.portfolioData= this.portfolioData.map(d => parseFloat(d.toFixed(2)));
    }  
  }

  getWalletBalance(){
    this.tradeService.getWalletBalance(this.clientService.verifyClient.clientId)
        .subscribe(wallet => this.walletBalance= wallet.walletBalance);
  }

  getInstrumentName(id: string): any{
    if (this.prices != null && this.prices.length > 0)
      return this.prices.find(ins => ins.instrument.instrumentId === id)?.instrument.instrumentDescription;
  }

  getInstrumentCategory(id: string): any{
    if (this.prices != null && this.prices.length > 0)
      return this.prices.find(ins => ins.instrument.instrumentId === id)?.instrument.categoryId;
  }

  getInstrumentBidPrice(id: string): any{
    if (this.prices != null && this.prices.length > 0)
      return this.prices.find(ins => ins.instrument.instrumentId === id)?.bidPrice;    
  }

  getInstrumentAskPrice(id: string): any{
    if (this.prices != null && this.prices.length > 0)
      return this.prices.find(ins => ins.instrument.instrumentId === id)?.askPrice;    
  }

  abs(num: number): number{
    return Math.abs(num)
  }

  ifLoggedIn() {
    console.debug(this.clientService.getIfLoggedIn());
    return !this.clientService.getIfLoggedIn();
  }

  clickTradeButton(instId: String, option: string) {
    this.location.navigate(['/instrument', instId, option]);
  }
}