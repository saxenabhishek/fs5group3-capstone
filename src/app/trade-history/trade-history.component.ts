import { Component, OnInit} from '@angular/core';
import { TradeService } from '../services/trade/trade.service';
import { Order } from '../models/order';
import { Direction } from '../models/direction';

@Component({
  selector: 'app-trade-history',
  templateUrl: './trade-history.component.html',
  styleUrls: ['./trade-history.component.css']
})
export class TradeHistoryComponent implements OnInit{
  trades: Order[]= [];
  date: string= `${new Date().toLocaleDateString('en-GB')}, ${new Date().toLocaleTimeString()} IST`

  constructor(private tradeService: TradeService) { }

  ngOnInit() {
    this.loadWholeTradeHistory();
  }

  loadWholeTradeHistory(){
    let clientId= "UID001";
    this.tradeService.getTradeHistory(clientId).subscribe(allTrades => {
      this.trades= allTrades.map(trade => ({
        ...trade,
        direction: { stringValue : trade.direction }
      }))
    });
  }
}
