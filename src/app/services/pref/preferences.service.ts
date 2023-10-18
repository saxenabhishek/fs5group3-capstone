import { Injectable } from '@angular/core';
import { Preference } from '../../models/preference';
import { Observable, catchError, of, throwError} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IntegerDTO } from 'src/app/models/integer-dto';
import { ClientService } from '../client/client.service';


@Injectable({
  providedIn: 'root'
})
export class PreferencesService {
  url='http://ec2-13-234-115-43.ap-south-1.compute.amazonaws.com:8080/client'; 
  newUser: boolean= false;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
  };
  
  constructor(private http: HttpClient, private clientService: ClientService) {}

  addPreference(preference: Preference): Observable<IntegerDTO> {
    console.log( preference );
    return this.http.post<IntegerDTO>(`${this.url}/preference/add`, preference, this.httpOptions)
            .pipe(
              catchError((error) => {
                console.error('API error for Add Preference (POST Request):', error);
                return throwError(() => error);
              })
            );
  } 

  getPreferenceById(id: string ): Observable<Preference> {
    return this.http.get<Preference>( `${this.url}/preference/${id}` )
    .pipe(
      catchError((error) => {
        console.error('API error for GET Preference (GET Request):', error);
        return throwError(() => error);
      })
    );
  }
  
  updatePreference( preference: Preference ): Observable<IntegerDTO>  {
    return this.http.put<IntegerDTO>( `${this.url}/preference/update`, preference, this.httpOptions )
    .pipe(
      catchError((error) => {
        console.error('API error for Update Preference (PUT Request):', error);
        return throwError(() => error);
      })
    );
  } 
}
  

