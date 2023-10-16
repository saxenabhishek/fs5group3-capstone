import { Injectable } from '@angular/core';
import { Client } from '../../models/client.model';
import { Observable, catchError, of, switchMap, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ClientFMTS } from 'src/app/models/client-fmts';
import { IntegerDTO } from 'src/app/models/integer-dto';

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
  ngOnInit(){
    this.checkIfClientIsLoggedIn()
  }
  checkIfClientIsLoggedIn() {
    const client: ClientFMTS = JSON.parse(localStorage.getItem("client") || "{}"); 
    const stringUnixTimestamp: string = localStorage.getItem("timestamp") || "0";
    
    let ts : Date =  new Date(parseInt(stringUnixTimestamp , 10)); 

    let dateNow: Date = new Date();
    const isWithinOneDay = (dateToCompare: Date): boolean => {
      const currentDate = new Date();
      const timeDifference = currentDate.getTime() - dateToCompare.getTime();
      return timeDifference <= 24 * 60 * 60 * 1000;
    };

    if(isWithinOneDay(ts)){
      this.verifyClient = client;
    }
    else{
      this.verifyClient = new ClientFMTS("", "");
      localStorage.removeItem("client")
      localStorage.removeItem("timestamp")
    }
  }

  currentClient?: Client = undefined;
  private login: boolean = false;

  registerNewClient(newClient: Client): Observable<Boolean> {
    console.log("REGISTERnew: ", newClient)
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
