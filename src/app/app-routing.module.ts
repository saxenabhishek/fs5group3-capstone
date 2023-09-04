import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterPageComponent } from './register-page/register-page.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { TradeHistoryComponent } from './trade-history/trade-history.component';
import { StockPageComponent } from './stock-page/stock-page.component';
import { ExplorePageComponent } from './explore-page/explore-page.component';

const routes: Routes = [
  { path: 'register', component: RegisterPageComponent },
  {
    path: 'portfolio',
    component: PortfolioComponent,
  },
  { path: 'trade-history', component: TradeHistoryComponent },
  { path: 'instruments', component: ExplorePageComponent },
  { path: 'instrument/:id', component: StockPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
