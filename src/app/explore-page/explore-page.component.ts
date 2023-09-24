import { Component } from '@angular/core';
import { TradeService } from '../services/trade/trade.service';
import { Instruments } from '../models/instruments';

@Component({
  selector: 'app-explore-page',
  templateUrl: './explore-page.component.html',
  styleUrls: ['./explore-page.component.css'],
})
export class ExplorePageComponent {
  constructor(private tradeService: TradeService) {}
  instruments: Instruments[] = [];
  ngOnInit() {
    this.tradeService
      .getInstruments()
      .subscribe((data) => (this.instruments = data));
  }
}
