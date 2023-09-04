import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { IdentificationTypes } from '../models/enums/IdentificationTypeEnum';
import { CountryCodes } from '../models/enums/CountryEnum';
import { PostalCodes } from '../models/enums/PostalCodeEnum';

//import { AccountService, AlertService } from '@app/_services';
@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})

export class RegisterPageComponent {
  registrationForm:FormGroup;
email: any;

// identityTypes = new Map<string, string[]>([
//     [CountryCodes.India, [IdentificationTypes.Aadhaar, IdentificationTypes.Passport]],
//     [CountryCodes.USA, [IdentificationTypes.SSN, IdentificationTypes.Passport]],
//     [CountryCodes.Ireland, [ IdentificationTypes.Passport]]
// ])
// postalCodes = new Map<string, string>([
//     [CountryCodes.India, PostalCodes.India],
//     [CountryCodes.USA, PostalCodes.USA],
//     [CountryCodes.Ireland, PostalCodes.Ireland]
// ])
// countryCodes: string[] = Array.from(this.identityTypes.keys());
//   constructor(private fb:FormBuilder){}
//   ngOnInit():void{
//     this.registrationForm=this.fb.group({
//       email:['',[Validators.required,Validators.email]]
//     });
//   }
  onSubmit()
  {
    if(this.registrationForm.valid)
    {

    }
  }

//   get email()
//   {
//     return this.registrationForm.get('email');
//   }
  // isEmailValidAndAvailable = false;
 formInfo:any={
  name: '',
  email: '',
  password:'',
  id:0,
  dob:'',
  country:'IN',
  postalCode:'',
  token:'',
  type:'',
  value:''
 }


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
  // constructor() { }
 
  
  // ngOnInit() {
  // }
  
  //valid = this.terms.nativeElement.checked;
  
  // onSignUp(form: NgForm) {
  //   // console.log(this.terms);
  //   // console.log(this.terms.nativeElement.checked);
  // }
}


