import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})

export class RegisterPageComponent {
registrationForm:FormGroup;

// const clientEmails=new Set<string>();
 constructor(private fb:FormBuilder) { }
 ngOnInit() {
  this.registrationForm=this.fb.group({
    country:['India',Validators.required],
    pin:[''],
    zip:[''],
    eircode:[''],
    aadhar:[''],
    ssn:[''],
    passport:[''],
    email: ['',Validators.compose([Validators.required,Validators.email])]
  })
  }
  

  onSubmit()
  {
    if(this.registrationForm.valid)
    {
     
    }
  }
 

  // get email()
  // {
  //   return this.registrationForm.get('email');
  // }
  // isEmailValidAndAvailable = false;
//  formInfo:any={
//   name: '',
//   email: '',
//   password:'',
//   id:0,
//   dob:'',
//   country:'IN',
//   postalCode:'',
//   token:'',
//   type:'',
//   value:''
//  }


//   onSubmit() {
//     if(this.registrationForm.valid)
//     {
//     console.log('Registration form submitted!');
//     console.log(`Name: ${this.name}`);
//     console.log(`Email: ${this.email}`);
//     console.log(`Id: ${this.id}`);
//     console.log(`Id: ${this.dob}`);
//     console.log(`Id: ${this.country}`);
//     console.log(`Id: ${this.postalcode}`);
//   }
// }
 
 
  
  
  
  
}


