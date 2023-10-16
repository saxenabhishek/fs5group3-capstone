import { Component } from '@angular/core';
import { TradeService } from '../services/trade/trade.service';
import { Instruments } from '../models/instruments';
import { ClientService } from '../services/client/client.service';
import { Location } from '@angular/common';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-explore-page',
  templateUrl: './explore-page.component.html',
  styleUrls: ['./explore-page.component.css'],
})
export class ExplorePageComponent {
  constructor(
    private tradeService: TradeService,
    private clientService: ClientService,
    private location: Router
  ) {}
  instruments: Instruments[] = [];
  ngOnInit() {
    this.tradeService.getInstruments().subscribe((data) => {
      this.instruments = data;
      console.debug(this.instruments);
    });
  }

  ifLoggedIn() {
    console.debug(this.clientService.getIfLoggedIn());
    return !this.clientService.getIfLoggedIn();
  }

  clickedBuyButton(instId: String) {
    this.location.navigate(['/instrument', instId, "buy"]);
  }
}
