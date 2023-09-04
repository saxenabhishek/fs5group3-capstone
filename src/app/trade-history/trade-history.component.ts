import { Component, OnInit} from '@angular/core';
import { TradeService } from '../services/trade.service';
import { Order } from '../models/order';

const testData: Order[]= 
[
  {
    "instrument": "AAPS",
    "quantity": 1000,
    "targetPrice": 7.8625,
    "direction": { 
      "stringValue": "B" 
    },
    "clientId": "UID001",
    "orderId": "OID001"
  },
  {
    "instrument": "AAPS",
    "quantity": 1000,
    "targetPrice": 7.8625,
    "direction": { 
      "stringValue": "B" 
    },
    "clientId": "UID001",
    "orderId": "OID001"
  },
  {
    "instrument": "AAPS",
    "quantity": 1000,
    "targetPrice": 7.8625,
    "direction": { 
      "stringValue": "S" 
    },
    "clientId": "UID001",
    "orderId": "OID001"
  },
  {
    "instrument": "AAPS",
    "quantity": 1000,
    "targetPrice": 7.8625,
    "direction": { 
      "stringValue": "S" 
    },
    "clientId": "UID001",
    "orderId": "OID001"
  },
  {
    "instrument": "AAPS",
    "quantity": 1000,
    "targetPrice": 7.8625,
    "direction": { 
      "stringValue": "B" 
    },
    "clientId": "UID001",
    "orderId": "OID001"
  },
  {
    "instrument": "AAPS",
    "quantity": 1000,
    "targetPrice": 7.8625,
    "direction": { 
      "stringValue": "S" 
    },
    "clientId": "UID001",
    "orderId": "OID001"
  }
];

@Component({
  selector: 'app-trade-history',
  templateUrl: './trade-history.component.html',
  styleUrls: ['./trade-history.component.css']
})
export class TradeHistoryComponent implements OnInit{
  orders: Order[]= [];

  constructor(private tradeService: TradeService) { }

  ngOnInit() {
    this.loadAllTrades();
  }

  loadAllTrades(){
    this.orders= testData;
  }
}
