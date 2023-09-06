import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterPageComponent } from './register-page/register-page.component';
import { DemoLandingComponent } from './demo-landing/demo-landing.component';

const routes: Routes = [
  {path:'register',component:RegisterPageComponent},
  {path:'',component: DemoLandingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
