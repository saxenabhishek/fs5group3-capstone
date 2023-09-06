import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterPageComponent } from './register-page/register-page.component';
import { DemoLandingComponent } from './demo-landing/demo-landing.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { TradeHistoryComponent } from './trade-history/trade-history.component';
import { LoginPageComponent } from './login-page/login-page.component';

const routes: Routes = [
  {
    path:'',
    component: DemoLandingComponent
  },  
  {
    path:'register',
    component: RegisterPageComponent
  },
  {
    path:'login',
    component: LoginPageComponent
  },
  {
    path:'portfolio',
    component: PortfolioComponent
  },
  {
    path:'trade-history',
    component: TradeHistoryComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
