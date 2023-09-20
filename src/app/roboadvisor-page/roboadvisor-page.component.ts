import { Component } from '@angular/core';
import { MockRoboadvisor } from '../models/mock-roboadvisor';
import { RoboadvisorService } from '../services/roboadvisor.service';

@Component({
  selector: 'app-roboadvisor-page',
  templateUrl: './roboadvisor-page.component.html',
  styleUrls: ['./roboadvisor-page.component.css']
})
export class RoboadvisorPageComponent {
roboadvisor:MockRoboadvisor[]=[];
constructor(private roboadvisorService : RoboadvisorService) { }
ngOnInit() {
  this.loadData();
}

loadData() {
  this.roboadvisorService.getData().subscribe(data => this.roboadvisor = data);
}
}
