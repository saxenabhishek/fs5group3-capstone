import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
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
  // ? do email unique and user registered do the same thing
  // emailUnique: number= 0;
  userRegistered: boolean= false;
  showSuccessDiv: boolean= false;
  showErrorDiv: boolean= false;
  submitted: boolean= false;
  currentDate: string= "";


  constructor(
    private fb: FormBuilder, 
    private clientService: ClientService,
    private router: Router
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
          console.log("FLAG", flag)
          if (flag == 0){
            if(this.clientService.registerNewClient(newClient)){
              this.userRegistered= true;
              this.showSuccessDiv= true;
    
              // Makes success msg div disappear after 3 seconds
              // setTimeout(() => this.showSuccessDiv = false, 3000);
              setTimeout(() => {
                this.showSuccessDiv = false;
                this.router.navigate(['/login']);
                this.registrationForm.reset();
              }, 2000);
            }
          }
          else{
            console.log("User Exists");
            this.showErrorDiv = true;
            setTimeout(() => this.showErrorDiv = false, 2000);
          }          
        });
      // console.log(this.emailUnique)
      // if (this.emailUnique == 0){
      //   if(this.clientService.registerNewClient(newClient)){
      //     this.userRegistered= true;
      //     this.showSuccessDiv= true;
      //     this.registrationForm.reset();

      //     // Makes success msg div disappear after 3 seconds
      //     setTimeout(() => this.showSuccessDiv = false, 3000);
      //     setTimeout(() => {
      //       this.showSuccessDiv = false;
      //       this.router.navigate(['/login']);
      //     }, 3000);
      //   }
      // }
      // else{
      //   console.log("User Exists");
      //   this.showErrorDiv = true;
      //   setTimeout(() => this.showErrorDiv = false, 3000);
      // }
    }
  }
}
