import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from '../services/client/client.service';
import { catchError, throwError } from 'rxjs';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  loginForm = new FormGroup({
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

  submitted: boolean = false;
  showSuccessDiv: boolean= false;
  showErrorDiv: boolean= false;  
  
  constructor(private clientService: ClientService, private router: Router) {}

  get loginData() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (!this.loginForm.invalid) {
      this.clientService.verifyClientInfo(
        this.loginForm.get('email')?.value || '',
        this.loginForm.get('password')?.value || ''
      )
      .pipe(
        catchError((error: any) => {
          this.showErrorDiv = true;
          this.submitted= false;
          console.error('API error for Login (POST Request):', error);
          return throwError(() => error);
      }))
      .subscribe(() => {
          this.showSuccessDiv = true;
          this.showErrorDiv = false;
          
          setTimeout(() => {
            this.showSuccessDiv = false;
            this.submitted= false;
            this.router.navigate(['/preferences/add']);          
            this.loginForm.reset();
          }, 1000);   
      });  
    }
    else{
      alert("Invalid Action !");
    }
  }
} 
