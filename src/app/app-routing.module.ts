import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterPageComponent } from './register-page/register-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ReportPageComponent } from './report-page/report-page.component';
import { RoboadvisorPageComponent } from './roboadvisor-page/roboadvisor-page.component';


const routes: Routes = [
  {path:'register',component:RegisterPageComponent},
  {path:'login',component:LoginPageComponent},
  {path:'report',component:ReportPageComponent},
  {path:'roboadvisor',component:RoboadvisorPageComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
