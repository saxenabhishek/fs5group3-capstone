import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component'
import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { PortfolioPageComponent } from './portfolio-page/portfolio-page.component';
import { InvestmentPreferComponent } from './investment-prefer/investment-prefer.component';
import { InvestmentPreferUpdateComponent } from './investment-prefer-update/investment-prefer-update.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginPageComponent,
    RegisterPageComponent,
    PortfolioPageComponent,
    InvestmentPreferComponent,
    InvestmentPreferUpdateComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule 

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
