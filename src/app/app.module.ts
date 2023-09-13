import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { StockPageComponent } from './stock-page/stock-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgApexchartsModule } from 'ng-apexcharts';
import { ExplorePageComponent } from './explore-page/explore-page.component';
import { HttpClientModule } from '@angular/common/http';
import { ReportPageComponent } from './report-page/report-page.component';
import { RoboadvisorPageComponent } from './roboadvisor-page/roboadvisor-page.component';
import { DemoLandingComponent } from './demo-landing/demo-landing.component';
import { TradeHistoryComponent } from './trade-history/trade-history.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { NavbarComponent } from './navbar/navbar.component';
import { InvestmentPreferComponent } from './investment-prefer/investment-prefer.component';
import { InvestmentPreferUpdateComponent } from './investment-prefer-update/investment-prefer-update.component';
import { PortfolioChartComponent } from './portfolio-chart/portfolio-chart.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterPageComponent,
    StockPageComponent,
    ExplorePageComponent,
    ReportPageComponent,
    RoboadvisorPageComponent,
    DemoLandingComponent,
    TradeHistoryComponent,
    PortfolioComponent,
    NavbarComponent,
    InvestmentPreferComponent,
    InvestmentPreferUpdateComponent,
    PortfolioChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    NgApexchartsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
