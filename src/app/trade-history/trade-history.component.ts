import { Component, OnInit} from '@angular/core';
import { TradeService } from '../services/trade/trade.service';
import { Order } from '../models/order';

@Component({
  selector: 'app-trade-history',
  templateUrl: './trade-history.component.html',
  styleUrls: ['./trade-history.component.css']
})
export class TradeHistoryComponent implements OnInit{
  orders: Order[]= [];
  date: string= `${new Date().toLocaleDateString('en-GB')}, ${new Date().toLocaleTimeString()} IST`

  constructor(private tradeService: TradeService) { }

  ngOnInit() {
    this.loadAllOrders();
  }

  loadAllOrders(){
    this.tradeService.getOrders().subscribe(orders => this.orders= orders);
  }
}
