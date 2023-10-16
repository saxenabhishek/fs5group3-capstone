import { Component } from '@angular/core';
import { ReportsService } from '../services/report/reports.service';
import { Order } from '../models/order';
import { ClientService } from '../services/client/client.service';

@Component({
  selector: 'app-report-page',
  templateUrl: './report-page.component.html',
  styleUrls: ['./report-page.component.css']
})
export class ReportPageComponent {
  reports: Order[] = [];
  selectedReport: boolean= false;

  constructor(private reportsService: ReportsService, private clientService: ClientService) { }
 
  loadBuyReport() {
    this.selectedReport= true;
    this.reportsService.getReport(this.clientService.verifyClient.clientId, "BUY")
        .subscribe(response => this.reports = response);
  }

  loadSellReport() {
    this.selectedReport= true;
    this.reportsService.getReport(this.clientService.verifyClient.clientId, "SELL")
        .subscribe(response => this.reports = response);
  }
}
