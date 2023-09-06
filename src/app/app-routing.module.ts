import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterPageComponent } from './register-page/register-page.component';

import { StockPageComponent } from './stock-page/stock-page.component';
import { ExplorePageComponent } from './explore-page/explore-page.component';
import { DemoLandingComponent } from './demo-landing/demo-landing.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { TradeHistoryComponent } from './trade-history/trade-history.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ReportPageComponent } from './report-page/report-page.component';
import { RoboadvisorPageComponent } from './roboadvisor-page/roboadvisor-page.component';

const routes: Routes = [
  {
    path: '',
    component: DemoLandingComponent,
  },
  {
    path: 'register',
    component: RegisterPageComponent,
  },
  {
    path: 'login',
    component: LoginPageComponent,
  },
  {
    path: 'portfolio',
    component: PortfolioComponent,
  },
  {
    path: 'trade-history',
    component: TradeHistoryComponent,
  },
  {
    path: 'report',
    component: ReportPageComponent,
  },
  {
    path: 'roboadvisor',
    component: RoboadvisorPageComponent,
  },
  { path: 'instruments', component: ExplorePageComponent },
  { path: 'instrument/:id', component: StockPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
