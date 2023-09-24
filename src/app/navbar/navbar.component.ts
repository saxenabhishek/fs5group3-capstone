import { Component, Injectable } from '@angular/core';
import { ClientService } from '../services/client/client.service';

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

  constructor(private clientService: ClientService) {}

  isLoggedIn(){
    return this.clientService.getIfLoggedIn()
  }

  changeLogin(flag: boolean){
    this.login= flag;
    console.debug(this.login)
  }
}
