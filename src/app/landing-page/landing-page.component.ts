import { Component } from '@angular/core';
import { ClientService } from '../services/client/client.service';
import { ClientFMTS } from '../models/client-fmts';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {

  constructor(private clientService: ClientService, private toastr: ToastrService) {}

  ngOnInit(){
    this.clientService.checkIfClientIsLoggedIn()
  }

  featuredAssets: any[] = [
    {
      name: 'Alphabet Stocks',
      description: 'Invest in the stocks of Alphabet for great returns.',
      imageUrl: 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/Alphabet_Inc_Logo_2015.svg/2560px-Alphabet_Inc_Logo_2015.svg.png' 
    },
    {
      name: 'Bonds',
      description: 'Buy and trade popular Government Bonds',
      imageUrl: 'https://images-media.currency.com/5ceb14e6/df72/50c3/aabb/b6c7f7fcd05e/on_page/treasury-bond-coupon.jpg' 
    },
    {
      name: 'Alphabet Stocks',
      description: 'Invest in the stocks of Alphabet for great returns.',
      imageUrl: 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/Alphabet_Inc_Logo_2015.svg/2560px-Alphabet_Inc_Logo_2015.svg.png' 
    }
  ];

  isLoggedIn(){
    return this.clientService.getIfLoggedIn();
  }

  LogOut(){
    this.toastr.success('You are logged out', 'Success');
    if(this.isLoggedIn())
      setTimeout(() => this.clientService.verifyClient= new ClientFMTS("", ""), 1000);
  }
}
