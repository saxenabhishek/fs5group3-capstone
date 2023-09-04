import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterPageComponent } from './register-page/register-page.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { TradeHistoryComponent } from './trade-history/trade-history.component';

const routes: Routes = [
  { path: 'register', component: RegisterPageComponent },
  {
    path: 'portfolio',
    component: PortfolioComponent,
  },
  {
    path: 'trade-history',
    component: TradeHistoryComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
