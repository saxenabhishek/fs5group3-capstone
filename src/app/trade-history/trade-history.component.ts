import { Component, OnInit} from '@angular/core';
import { TradeService } from '../services/trade/trade.service';
import { Order } from '../models/order';
import { ClientService } from '../services/client/client.service';
import { ActivatedRoute } from '@angular/router';
import { Prices } from '../models/prices';

@Component({
  selector: 'app-trade-history',
  templateUrl: './trade-history.component.html',
  styleUrls: ['./trade-history.component.css']
})
export class TradeHistoryComponent implements OnInit{
  trades: Order[]= [];
  prices: Prices[]= [];
  itemsPerPage = 100; // Number of items per page
  currentPage = 1; // Current page

  get totalPages(): number {
    return Math.ceil(this.trades.length / this.itemsPerPage);
  }

  changePage(newPage: number) {
    if (newPage > 0 && newPage <= this.totalPages) {
      this.currentPage = newPage;
    }
  }
  constructor(private tradeService: TradeService, private clientService: ClientService,
                private route: ActivatedRoute) { 
    const resolvedData = this.route.snapshot.data['prices'];
    if (resolvedData) 
      this.prices = resolvedData;
  }

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

  getInstrumentName(id: string): any{
    if (this.prices != null && this.prices.length > 0)
      return this.prices.find(ins => ins.instrument.instrumentId === id)?.instrument.instrumentDescription;
  }

  getInstrumentCategory(id: string): any{
    if (this.prices != null && this.prices.length > 0)
      return this.prices.find(ins => ins.instrument.instrumentId === id)?.instrument.categoryId;
  }

  getInstrumentAskPrice(id: string): any{
    if (this.prices != null && this.prices.length > 0)
      return this.prices.find(ins => ins.instrument.instrumentId === id)?.askPrice;    
  }
}
