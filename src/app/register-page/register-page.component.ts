import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Person } from '../models/person.model';
import { ClientIdentification } from '../models/client-identification.model';
import { Client } from '../models/client.model';
import { ClientService } from '../services/client/client.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css'],
})
export class RegisterPageComponent {
  registrationForm: FormGroup = new FormGroup({});
  clients: Client[]= [];
  // ? do email unique and user registered do the same thing
  emailUnique: boolean= true;
  userRegistered: boolean= false;
  showDiv: boolean= false;

  constructor(
    private fb: FormBuilder, 
    private clientService: ClientService
    ) {}

  ngOnInit() {
    this.registrationForm = this.fb.group({
      fName: ['', Validators.required],
      lName: [''],
      dob: ['', Validators.required],
      country: ['', Validators.required],
      postalCode: ['', Validators.required],
      identification: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.required],
    });

    this.loadAllClients();
  }

  onSubmit() {
    if(this.registrationForm.valid)
    {
      let newUser= new Person(
        this.clients[this.clients.length-1].person.id + 1, 
        this.registrationForm.get('country')?.value,
        this.registrationForm.get('postalCode')?.value,
        this.registrationForm.get('dob')?.value,
        this.registrationForm.get('email')?.value,
        this.registrationForm.get('password')?.value
      );
      let newUserID= new ClientIdentification(
        this.registrationForm.get('country')?.value === 'india' ? 'Aadhaar' : 
          (this.registrationForm.get('country')?.value === 'us' ? 'SSN' : 'Passport'),
        this.registrationForm.get('identification')?.value
      );
      let newClient= new Client(
        newUser,
        newUserID
      );
      if(this.clientService.registerNewClient(newClient)){
        this.userRegistered= true;
        this.showDiv= true;
        this.emailUnique= true;
        this.registrationForm.reset();
        // Makes success msg div disappear after 5 seconds
        setTimeout(() => this.showDiv = false, 5000);

        //todo: redirect to login page;
      }
      else{
        console.log("User Exists");
        this.emailUnique= false;
      }
    }
  }

  loadAllClients(){
    this.clientService.getClients()
      .subscribe(data => this.clients= data);
  }
}
