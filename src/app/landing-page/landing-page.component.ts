import { Component } from '@angular/core';
import { ClientService } from '../services/client/client.service';
import { ClientFMTS } from '../models/client-fmts';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {
  email: string= "";
  constructor(private clientService: ClientService, private toastr: ToastrService, private route: Router) {}

  ngOnInit(){
    this.clientService.checkIfClientIsLoggedIn()
    this.email= this.clientService.currentClientEmail ? this.clientService.currentClientEmail : "";
  }

  featuredAssets: any[] = [
    {
      name: 'Stocks',
      description: 'Invest in multitude of stocks for greater returns',
      imageUrl: 'https://freepngimg.com/thumb/stock_market/25739-9-stock-market-transparent-image.png' 
    },
    {
      name: 'Bonds',
      description: 'Seek stability !?  Come trade in popular government bonds',
      imageUrl: 'https://images-media.currency.com/5ceb14e6/df72/50c3/aabb/b6c7f7fcd05e/on_page/treasury-bond-coupon.jpg' 
    },
    {
      name: 'Certificate of Deposit (CD)',
      description: 'Invest in CDs and secure your future plans',
      imageUrl: 'https://www.pngkit.com/png/detail/281-2813050_certificate-of-deposit-certificate-of-deposit-icon.png' 
    }
  ];

  isLoggedIn(){
    return this.clientService.getIfLoggedIn();
  }

  LogOut(){    
    this.email= "";
    this.clientService.currentClientEmail= "";
    this.clientService.verifyClient = new ClientFMTS("", "");
    localStorage.removeItem("client");
    localStorage.removeItem("timestamp");
    this.toastr.success('You are logged out', 'Success');
    this.route.navigate(["/"]);
  }
}
