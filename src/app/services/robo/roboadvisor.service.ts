import { Injectable } from '@angular/core';
import { MockRoboadvisor } from '../../models/mock-roboadvisor';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Trade } from 'src/app/models/trade';

@Injectable({
  providedIn: 'root'
})
export class RoboadvisorService {
  private apiUrl="http://ec2-13-234-115-43.ap-south-1.compute.amazonaws.com:8080/trade";

  constructor(private http: HttpClient) { }

  getData(clientId: string): Observable<Trade[]> {  
    return this.http.get<Trade[]>(`${this.apiUrl}/robo-advisor/${clientId}`);
  }
}
