import { Component, Injectable} from '@angular/core';
import { ClientService } from '../services/client/client.service';
import { PreferencesService } from '../services/pref/preferences.service';
import { Preference } from '../models/preference';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class NavbarComponent {
  // login: boolean= false;
  newUser: boolean= false;

  constructor(private clientService: ClientService, private prefService: PreferencesService) {}

  isLoggedIn(){
    this.newUser= this.prefService.newUser;
    return this.clientService.getIfLoggedIn();
  }

  checkIfLoggedIn(){
    this.clientService.checkIfClientIsLoggedIn();
  }

  // changeLogin(flag: boolean){
  //   this.login= flag;
  //   console.debug(this.login)
  // }
}
