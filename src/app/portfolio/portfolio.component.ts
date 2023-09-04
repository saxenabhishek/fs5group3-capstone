import { Component, OnInit } from '@angular/core';
import { Trade } from '../models/trade';
import { TradeService } from '../services/trade.service';

const testData: Trade[]= 
[
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
    }  
];

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit{
  trades: Trade[]= [];

  constructor(private tradeService: TradeService) { }

  ngOnInit() {
    this.loadAllTrades();
  }

  loadAllTrades(){
    this.trades= testData;
  }
}