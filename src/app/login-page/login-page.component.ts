import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from '../services/client/client.service';
import { catchError, throwError } from 'rxjs';
import { PreferencesService } from '../services/pref/preferences.service';
import { NavbarComponent } from '../navbar/navbar.component'

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
  
  constructor(private clientService: ClientService, private router: Router, 
              private prefService: PreferencesService, private navbar: NavbarComponent) {}

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
            this.prefService.getPreferenceById(this.clientService.verifyClient.clientId)
                .subscribe(preference => {
                  if (preference == null){
                    this.prefService.newUser= true;
                    this.router.navigate(['/preferences/add']);                     
                  }       
                  else{
                    this.prefService.newUser= false;
                    this.router.navigate(['/']); 
                  } 
                });
            this.loginForm.reset();
          }, 1000);   
      });  
    }
    else{
      alert("Invalid Action !");
    }
  }
} 
