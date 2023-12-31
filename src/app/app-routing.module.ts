import { NgModule, inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, RouterModule, RouterStateSnapshot, Routes, UrlSerializer } from '@angular/router';
import { ExplorePageComponent } from './explore-page/explore-page.component';
import { InvestmentPreferUpdateComponent } from './investment-prefer-update/investment-prefer-update.component';
import { InvestmentPreferComponent } from './investment-prefer/investment-prefer.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { ReportPageComponent } from './report-page/report-page.component';
import { RoboadvisorPageComponent } from './roboadvisor-page/roboadvisor-page.component';
import { ClientService } from './services/client/client.service';
import { StockPageComponent } from './stock-page/stock-page.component';
import { TradeHistoryComponent } from './trade-history/trade-history.component';
import { PricesResolver } from './prices-resolver';


const routeGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state:RouterStateSnapshot) => {
  return inject(ClientService).getIfLoggedIn() || inject(UrlSerializer).parse("/");
};

const routes: Routes = [
  {
    path: '',
    component: LandingPageComponent,
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
    canActivate:[routeGuard],
    resolve: {
      prices: PricesResolver,
    }
  },
  {
    path: 'preferences/add',
    component: InvestmentPreferComponent,  
    canActivate:[routeGuard]  
  },
  {
    path: 'preferences/update',
    component: InvestmentPreferUpdateComponent,
    canActivate:[routeGuard]
  },
  {
    path: 'trade-history',
    component: TradeHistoryComponent,
    canActivate:[routeGuard],
    resolve: {
      prices: PricesResolver,
    }
  },
  {
    path: 'report',
    component: ReportPageComponent,
    canActivate:[routeGuard]
  },
  {
    path: 'roboadvisor',
    component: RoboadvisorPageComponent,
    canActivate:[routeGuard]
  },
  { 
    path: 'explore', 
    component: ExplorePageComponent 
  },
  { 
    path: 'instrument/:id/:option', 
    component: StockPageComponent 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
