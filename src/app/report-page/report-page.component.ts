import { Component } from '@angular/core';
import { ReportsService } from '../services/report/reports.service';
import { MockReport } from '../models/mock-report';
import { Order } from '../models/order';

@Component({
  selector: 'app-report-page',
  templateUrl: './report-page.component.html',
  styleUrls: ['./report-page.component.css']
})
export class ReportPageComponent {
  reports: Order[] = [];
  // availableReports:string[]=[
  //   'Trade Confirmation',
  //   'Account Statements',
  //   'Tax Documents',
  //   'Order Confirmation',
  //   'Trade Settlement Statement'
  // ];
  selectedReport: string = "";
  constructor(private reportsService: ReportsService) { }
 

  // loadReport() {
  //   this.reportsService.getReport().subscribe(data => this.reports = data);
  // }
  loadReport() {
    this.reportsService.getReport().subscribe(response => this.reports = response);
  }
  loadSellReport() {
    this.reportsService.getReport().subscribe(response => this.reports = response);
  }
}
