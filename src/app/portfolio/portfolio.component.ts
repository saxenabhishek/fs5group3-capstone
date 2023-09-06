import { Component, OnInit } from '@angular/core';
import { Trade } from '../models/trade';
import { TradeService } from '../services/trade.service';

const testData: Trade[]= 
[
  {
    "order": {
      "instrument": "GOOGL",
      "quantity": 900,
      "targetPrice": 6.673,
      "direction": { 
        "stringValue": "B" 
      },
      "clientId": "UID001",
      "orderId": "OID003"
    },
    "cashValue": 6005.7,
    "quantity": 900,
    "direction": { 
      "stringValue": "B" 
    },
    "executionPrice": 6.673,
    "instrumentId": "GOOGL",
    "clientId": "UID001",
    "tradeId": "TRX011"
  },
    {
      "order": {
        "instrument": "AAPS",
        "quantity": 1000,
        "targetPrice": 7.8625,
        "direction": { 
          "stringValue": "B" 
        },
        "clientId": "UID001",
        "orderId": "OID001"
      },
      "cashValue": 7862.5,
      "quantity": 1000,
      "direction": { 
        "stringValue": "B" 
      },
      "executionPrice": 7.8625,
      "instrumentId": "AAPS",
      "clientId": "UID001",
      "tradeId": "TRX011"
    },
    {
      "order": {
        "instrument": "AMD",
        "quantity": 100,
        "targetPrice": 7.745,
        "direction": { 
          "stringValue": "B" 
        },
        "clientId": "UID001",
        "orderId": "OID002"
      },
      "cashValue": 774.5,
      "quantity": 100,
      "direction": { 
        "stringValue": "B" 
      },
      "executionPrice": 7.745,
      "instrumentId": "AMD",
      "clientId": "UID001",
      "tradeId": "TRX012"
    },
    {
      "order": {
        "instrument": "AAPS",
        "quantity": 1000,
        "targetPrice": 7.8625,
        "direction": { 
          "stringValue": "B" 
        },
        "clientId": "UID001",
        "orderId": "OID001"
      },
      "cashValue": 7862.5,
      "quantity": 1000,
      "direction": { 
        "stringValue": "B" 
      },
      "executionPrice": 7.8625,
      "instrumentId": "AAPS",
      "clientId": "UID001",
      "tradeId": "TRX011"
    },
    {
      "order": {
        "instrument": "GOOGL",
        "quantity": 900,
        "targetPrice": 6.673,
        "direction": { 
          "stringValue": "B" 
        },
        "clientId": "UID001",
        "orderId": "OID003"
      },
      "cashValue": 6005.7,
      "quantity": 900,
      "direction": { 
        "stringValue": "B" 
      },
      "executionPrice": 6.673,
      "instrumentId": "GOOGL",
      "clientId": "UID001",
      "tradeId": "TRX011"
    }  
];

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit{
  trades: Trade[]= [];
  totalHoldings: number= 0;
  totalCashValue: number= 0;
  currentPrice1: number= 7.745;

  constructor(private tradeService: TradeService) { }

  ngOnInit() {
    this.loadAllTrades();
    this.calcTotalHoldings();
    this.calcTotalCashValue();
  }

  calcTotalHoldings(){
    testData.forEach(trade => this.totalHoldings+= (1 + ((this.currentPrice1-trade.executionPrice) / trade.executionPrice)) * trade.cashValue);
  }

  calcTotalCashValue(){
    testData.forEach(trade => this.totalCashValue+=  trade.cashValue);
  }
  
  loadAllTrades(){
    this.trades= testData;
  }

  abs(num: number): number{
    return Math.abs(num)
  }
}