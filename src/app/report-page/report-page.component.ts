import { Component } from '@angular/core';
import { MockReport } from '../models/mock-report';
import { ReportsService } from '../services/reports.service';

@Component({
  selector: 'app-report-page',
  templateUrl: './report-page.component.html',
  styleUrls: ['./report-page.component.css']
})
export class ReportPageComponent {
  reports: MockReport[] = [];
  // availableReports:string[]=[
  //   'Trade Confirmation',
  //   'Account Statements',
  //   'Tax Documents',
  //   'Order Confirmation',
  //   'Trade Settlement Statement'
  // ];
  selectedReport: string = "";
  constructor(private reportsService: ReportsService) { }
  

  loadReport() {
    this.reportsService.getReport().subscribe(data => this.reports = data);
  }
}
