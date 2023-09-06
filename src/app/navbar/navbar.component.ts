import { Component, Injectable } from '@angular/core';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class NavbarComponent {
  login: boolean= false;

  // constructor(private clientService: ClientService) {}

  changeLogin(flag: boolean){
    this.login= flag;
    console.log(this.login)
  }
}
