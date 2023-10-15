import { Injectable } from '@angular/core';
import { Client } from '../../models/client.model';
import { Observable, catchError, of, switchMap, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ClientFMTS } from 'src/app/models/client-fmts';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  registerClient: ClientFMTS = new ClientFMTS("", "");
  verifyClient: ClientFMTS = new ClientFMTS("", "");
  clientUrl= "http://localhost:8080/client/";
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
  };

  constructor(private http: HttpClient) {}

  registerNewClient(newClient: Client): Observable<Boolean> {
    console.log("REGISTERnew: ", newClient)
    this.http
        .post<ClientFMTS>(this.clientUrl + "register", newClient, this.httpOptions)
        // .pipe(
        //   switchMap((data: ClientFMTS) => {
        //     this.registerClient = data;
        //     console.log("REGISTER CLIENT: ", this.registerClient)
        //     return of(this.getIfSuccessfullyRegistered());
        //   }))
        .pipe(
          catchError((error) => {
            console.error('API error for Register New Client (POST Request):', error);
            return throwError(() => error);
          }))
          .subscribe(data => this.registerClient= data);

          return of(this.getIfSuccessfullyRegistered())
  }

  verifyClientInfo(email?: string, password?: string): Observable<boolean> {
    return this.http
        .post<ClientFMTS>(this.clientUrl + "login", { email: email, password: password }, this.httpOptions)
        .pipe(
          switchMap((data: ClientFMTS) => {
            this.verifyClient = data;
            return of(this.getIfLoggedIn());
          }))
        .pipe(
          catchError((error: any) => {
            console.error('API error for Login (POST Request):', error);
            return throwError(() => error);
          }));                     
  }

  getIfLoggedIn(): boolean {    
    return this.verifyClient.clientId !== "" && this.verifyClient.token !== "";
  }

  getIfSuccessfullyRegistered(): boolean{
    return this.registerClient.clientId !== "" && this.registerClient.token !== "";
  }

  getClientId(): string{
    if(this.verifyClient)
      return this.verifyClient.clientId;
    
    throw new Error("Illegal state, no client is logged in")

  }

  verifyEmailAddress(email: string): Observable<number> {
    return this.http
        .get<number>(this.clientUrl + "verify-email/" + email)
        .pipe(
          catchError((error) => {
            console.error('API error for Login (POST Request):', error);
            return throwError(() => error);
          }));
  }
}
