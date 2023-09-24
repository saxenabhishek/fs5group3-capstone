import { Injectable } from '@angular/core';
import { Client } from '../../models/client.model';
import { Observable, of } from 'rxjs';
import { testClients } from '../../const/clients';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  clients = testClients;
  currentClient?: Client = undefined;

  registerNewClient(newClient: Client): boolean {
    // todo: put logic to check if a user with same email exists and if the ID is also the same.
    // rn only checks if client with same email does not exist
    if (
      this.clients.findIndex(
        (e: Client) => e.person.email == newClient.person.email
      ) == -1
    ) {
      this.addNewClient(newClient);
      return true;
    }
    return false;
  }
  private login: boolean = false;

  getClients(): Observable<Client[]> {
    return of(this.clients);
  }

  addNewClient(client: Client): void {
    this.clients.push(client);
  }

  verifyClientInfo(email?: string, password?: string): Boolean {
    this.currentClient = this.clients.find(
      (client: Client) =>
        client.person.email === email && client.person.password == password
    );
    return this.getIfLoggedIn()
  }

  getIfLoggedIn() {
    return this.currentClient != undefined;
  }

  getClientId(){
    if(this.currentClient){
      return this.currentClient.person.id
    }
    throw new Error("Illegal state, no client is logged in")

  }
}
