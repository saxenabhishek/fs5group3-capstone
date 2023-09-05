import { Injectable } from '@angular/core';
import { Client } from '../models/client.model';
import { Observable, of } from 'rxjs';

const testClients= [
  {
    person:{
      id: 1234561,
      country: 'India',
      postalCode: '831005',
      dob: '2002-09-08',
      email: 'rishav123@gmail.com',
      password: 'rishav123'
    },
    identification:{
      type: "Aadhaar", 
      value: "767762121456"
    }
  },
  {
    person:{
      id: 1234562,
      country: 'India',
      postalCode: '831001',
      dob: '2001-09-08',
      email: 'rishabh23@gmail.com',
      password: 'rishabh23'
    },
    identification:{
      type: "Aadhaar", 
      value: "767787121456"
    }
  },
  {
    person:{
      id: 1234563,
      country: 'US',
      postalCode: '63100',
      dob: '1998-10-08',
      email: 'MarkJ44@gmail.com',
      password: 'markjonas4'
    },
    identification:{
      type: "SSN", 
      value: "778628144"
    }
  },
  {
    person:{
      id: 1234564,
      country: 'US',
      postalCode: '33111',
      dob: '2000-01-28',
      email: 'doherty.J23@rediffmmail.com',
      password: 'doherty@23'
    },
    identification:{
      type: "Aadhaar", 
      value: "767762121456"
    }
  },
  {
    person:{
      id: 1234565,
      country: 'Ireland',
      postalCode: 'A65F4E2',
      dob: '1991-02-12',
      email: 'marknoble11@gmail.com',
      password: 'marknoble11'
    },
    identification:{
      type: "Passport", 
      value: "PW2121456"
    }
  },
  {
    person:{
      id: 1234566,
      country: 'Ireland',
      postalCode: 'B89F4F2',
      dob: '1988-05-11',
      email: 'robholding.56@gmail.com',
      password: 'robbie@56'
    },
    identification:{
      type: "Passport", 
      value: "LW8845319"
    }
  }
];

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor() { }

  getClients(): Observable<Client[]>{
    return of(testClients);
  }

  addNewClient(client: Client): void {
    testClients.push(client);
    console.log(testClients)
  }

  verifyEmail(email: string): Boolean{
    return !testClients.find(client => client.person.email === email);
  }
}
