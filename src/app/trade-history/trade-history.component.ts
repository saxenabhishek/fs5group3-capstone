import { Component, OnInit} from '@angular/core';
import { TradeService } from '../services/trade/trade.service';
import { Order } from '../models/order';
import { Direction } from '../models/direction';
import { ClientService } from '../services/client/client.service';

@Component({
  selector: 'app-trade-history',
  templateUrl: './trade-history.component.html',
  styleUrls: ['./trade-history.component.css']
})
export class TradeHistoryComponent implements OnInit{
  trades: Order[]= [];

  constructor(private tradeService: TradeService, private clientService: ClientService) { }

  ngOnInit() {
    this.loadWholeTradeHistory();
  }

  loadWholeTradeHistory(){
    this.tradeService.getTradeHistory(this.clientService.verifyClient.clientId)
    .subscribe(allTrades => {
      if(allTrades != null){
        this.trades= allTrades.map(trade => ({
          ...trade,
          direction: { stringValue : trade.direction }
        }))
      }
    });
  }
}
