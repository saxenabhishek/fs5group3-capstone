import { Component } from '@angular/core';
import { MockRoboadvisor } from '../models/mock-roboadvisor';
import { RoboadvisorService } from '../services/robo/roboadvisor.service';
import { Trade } from '../models/trade';
import { ClientService } from '../services/client/client.service';

@Component({
  selector: 'app-roboadvisor-page',
  templateUrl: './roboadvisor-page.component.html',
  styleUrls: ['./roboadvisor-page.component.css']
})
export class RoboadvisorPageComponent {
  roboadvisor:Trade[]=[];

  constructor(private roboadvisorService : RoboadvisorService, 
              private clientService: ClientService) { }
  
  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.roboadvisorService.getData(this.clientService.verifyClient.clientId)
        .subscribe(data => this.roboadvisor = data);
  }
}
