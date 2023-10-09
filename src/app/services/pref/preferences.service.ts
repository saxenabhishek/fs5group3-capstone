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
 
  updatePrefer:Preference=new Preference('College Fund','CONSERVATIVE','40,001-60,000','0-5 years')

  addPreference(preference: Preference) {
    console.log( preference );
    return this.http.post( `${this.url}/preference/add`, preference );
  } 
  getPreferenceById( id: any ) {
    return this.http.get( `${this.url}/preference/${id}` );
  }
  
  updatePreference( id: any, preference: any ) {
    return this.http.put( `${this.url}/preference/update/${id}`, preference );
  }
 
}
  

