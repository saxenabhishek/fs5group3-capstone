import { Component } from '@angular/core';
import { ClientService } from '../services/client/client.service';
import { ClientFMTS } from '../models/client-fmts';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {

  constructor(private clientService: ClientService) {}

  featuredAssets: any[] = [
    {
      name: 'Alphabet Stocks',
      description: 'Invest in the stocks of Alphabet for great returns.',
      imageUrl: 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/Alphabet_Inc_Logo_2015.svg/2560px-Alphabet_Inc_Logo_2015.svg.png' 
    },
    {
      name: 'Bitcoin',
      description: 'Buy and trade Bitcoin, the world\'s most popular cryptocurrency.',
      imageUrl: 'https://cdn.dribbble.com/users/81899/screenshots/1411041/attachments/205810/bitcoin.png?resize=400x300&vertical=center' 
    },
    {
      name: 'Alphabet Stocks',
      description: 'Invest in the stocks of Alphabet for great returns.',
      imageUrl: 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/Alphabet_Inc_Logo_2015.svg/2560px-Alphabet_Inc_Logo_2015.svg.png' 
    },
    {
      name: 'Bitcoin',
      description: 'Buy and trade Bitcoin, the world\'s most popular cryptocurrency.',
      imageUrl: 'https://cdn.dribbble.com/users/81899/screenshots/1411041/attachments/205810/bitcoin.png?resize=400x300&vertical=center' 
    }
  ];

  isLoggedIn(){
    return this.clientService.getIfLoggedIn();
  }

  LogOut(){
    if(this.isLoggedIn())
      setTimeout(() => this.clientService.verifyClient= new ClientFMTS("", ""), 1000);
  }
}
