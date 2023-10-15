import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Person } from '../models/person.model';
import { ClientIdentification } from '../models/client-identification.model';
import { Client } from '../models/client.model';
import { ClientService } from '../services/client/client.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css'],
})
export class RegisterPageComponent {
  registrationForm= new FormGroup({
    fName: new FormControl('', Validators.required),
    lName: new FormControl(''),
    dob: new FormControl('', [Validators.required, this.minimumAgeValidator(18)]),
    country: new FormControl("", Validators.required),
    postalCode: new FormControl('', Validators.required),
    identification: new FormControl('', Validators.required),
    email: new FormControl('', [
      Validators.required,
      Validators.maxLength(50),
      Validators.email,
    ]),
    password: new FormControl('', [
      Validators.required,
      Validators.pattern(
        '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$'
      )
    ])
  });

  clients: Client[]= [];
  submitted: boolean= false;
  currentDate: string= "";

  constructor(
    private clientService: ClientService,
    private router: Router, private toastr: ToastrService
    ) {
      this.currentDate = new Date().toISOString().split('T')[0];
    }

  get registrationData() {
    return this.registrationForm.controls;
  }

  minimumAgeValidator(minimumAge: number) {
    return (control: AbstractControl): { [key: string]: any } | null => {
      if (control.value) {
        const dateOfBirth = new Date(control.value);
        const today = new Date();
        const age = today.getFullYear() - dateOfBirth.getFullYear();
  
        if (age < minimumAge) 
          return { minimumAge: true }; // Validation failed        
      }
      return null; // Validation passed
    }
  }

  formatDOB(dob: string): string{
    const dobValue= new Date(dob);

    const year = dobValue.getFullYear();
    const month = String(dobValue.getMonth() + 1).padStart(2, '0');
    const day = String(dobValue.getDate()).padStart(2, '0');

    const formattedDOB = `${year}${month}${day}`;
    return formattedDOB;
  }

  onSubmit() {
    this.submitted= true;
    if(this.registrationForm.valid)
    {
      let newUser= new Person(
        "", 
        this.registrationForm.get('country')?.value || '',
        this.registrationForm.get('postalCode')?.value || '',
        this.formatDOB(this.registrationForm.get('dob')?.value || ''),
        this.registrationForm.get('email')?.value || '',
        this.registrationForm.get('password')?.value || ''
      );
      let newUserID= new ClientIdentification(
        this.registrationForm.get('country')?.value === 'India' ? 'Aadhaar' : 
          (this.registrationForm.get('country')?.value === 'United States' ? 'SSN' : 'Passport'),
        this.registrationForm.get('identification')?.value || ''
      );
      let idSet= new Array<ClientIdentification>();
      idSet.push(newUserID);
      let newClient= new Client(
        newUser,
        idSet
      );
      
      this.clientService.verifyEmailAddress(newClient.person.email)
        .subscribe(flag => {
          if (flag == 0){
            if(this.clientService.registerNewClient(newClient)){
              this.toastr.success('You are successfully registered', 'Success'); 
              
              setTimeout(() => {
                this.router.navigate(['/login']);
                this.registrationForm.reset();
              }, 2000);
            }
          }
          else
            this.toastr.error('User Exists !', 'Error');       
        });
    }
    else
      this.toastr.error('Please fill the form correctly', 'Error');
  }
}
