import { Injectable } from '@angular/core';
import { Preference } from '../../models/preference';
import { Observable, of} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PreferencesService {
  preference!:Preference;
  updatePrefer:Preference=new Preference('College Fund','CONSERVATIVE','40,001-60,000','0-5 years')

  addPreference(preference: Preference): Observable<Preference> {
    this.preference = preference;
    return of(preference); 
  } 
  
  getPreference():Observable<Preference> { 
    
    return of(this.updatePrefer);  

  }}
 

  

