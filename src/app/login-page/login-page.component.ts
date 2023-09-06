import { Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent  {
  loginForm=new FormGroup({
    email:new FormControl('',[ Validators.required,Validators.maxLength(50),Validators.email]),
    password:new FormControl('', [Validators.required,Validators.pattern(
      '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$'
    )])
  });
  submitted = false;

  get loginData(){
    return this.loginForm.controls;
  }
  onSubmit() {
    this.submitted = true;
    console.log(this.loginForm);

    // stop here if form is invalid
    if (this.loginForm.invalid) {

        return;
    }
  }
  
}//