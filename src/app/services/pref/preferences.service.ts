import { Injectable } from '@angular/core';
import { Preference } from '../../models/preference';
import { Observable, of} from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class PreferencesService {
  url='http://localhost:8080/client'; 
  constructor( private http: HttpClient ) { }
 
  

  addPreference(preference: Preference) {
    console.log( preference );
    return this.http.post( `${this.url}/preference/add`, preference );
  } 
  getPreferenceById( id: string ) {
    return this.http.get( `${this.url}/preference/${id}` );
  }
  
  updatePreference( preference: any ) {
    return this.http.put( `${this.url}/preference/update`, preference );
  }
 
}
  

