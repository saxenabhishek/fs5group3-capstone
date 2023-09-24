import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ClientService } from '../services/client/client.service';

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
      ),
    ]),
  });
  submitted = false;
  errorMsg?: String = undefined;

  constructor(private clientService: ClientService, private router: Router) {}

  get loginData() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      let isvalid = this.clientService.verifyClientInfo(
        this.loginForm.get('email')?.value || '',
        this.loginForm.get('password')?.value || ''
      );
      if (isvalid) {
        // todo: Make UI change to show login success
        alert('logged in');
        this.router.navigate(['']);
      } else {
        // todo: Do styling for error message
        this.errorMsg = 'Invalid Login';
      }
    }
  }
} //
