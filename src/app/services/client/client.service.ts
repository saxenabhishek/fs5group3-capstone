import { Injectable } from '@angular/core';
import { Client } from '../../models/client.model';
import { Observable, catchError, of, switchMap, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ClientFMTS } from 'src/app/models/client-fmts';
import { IntegerDTO } from 'src/app/models/integer-dto';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  registerClient: ClientFMTS = new ClientFMTS("", "");
  verifyClient: ClientFMTS = new ClientFMTS("", "");
  currentClientEmail?: string = "";

  clientUrl= "http://localhost:8080/client/";
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
  };

  constructor(private http: HttpClient, private toastr: ToastrService, private route: Router) {}
  
  ngOnInit(){
    this.checkIfClientIsLoggedIn()
  }

  checkIfClientIsLoggedIn() {
    const client: ClientFMTS = JSON.parse(localStorage.getItem("client") || "{}"); 
    const stringUnixTimestamp: string = localStorage.getItem("timestamp") || "0";
    
    let ts : Date =  new Date(parseInt(stringUnixTimestamp , 10)); 

    const isWithinExpiryTime = (dateToCompare: Date): boolean => {
      const currentDate = new Date();
      const timeDifference = currentDate.getTime() - dateToCompare.getTime();
      return timeDifference <= 12 * 60 * 60 * 1000;
    };

    if(isWithinExpiryTime(ts)){
      this.verifyClient = client;
    }
    else{
      if(this.currentClientEmail != ""){
        this.currentClientEmail= "";
        this.toastr.warning("You have been logged out", "Session Expired !");
      }
      this.verifyClient = new ClientFMTS("", "");
      localStorage.removeItem("client");
      localStorage.removeItem("timestamp");
      this.route.navigate(["/"]);
    }
  }

  registerNewClient(newClient: Client): Observable<Boolean> {
    this.http
        .post<ClientFMTS>(this.clientUrl + "register", newClient, this.httpOptions)
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
            this.currentClientEmail= email ? email : "";
            this.verifyClient = data;
            localStorage.setItem("client", JSON.stringify(this.verifyClient))
            localStorage.setItem("timestamp", Date.now().toString())
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
        .get<IntegerDTO>(this.clientUrl + "verify-email/" + email)
        .pipe(
          switchMap((data: IntegerDTO) => of(data.rowCount)))
        .pipe(
          catchError((error) => {
            console.error('API error for Login (POST Request):', error);
            return throwError(() => error);
          }));
  }
}
