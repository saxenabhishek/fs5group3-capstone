import { Component } from '@angular/core';

@Component({
  selector: 'app-demo-landing',
  templateUrl: './demo-landing.component.html',
  styleUrls: ['./demo-landing.component.css']
})
export class DemoLandingComponent {
  login: boolean= false;
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

  changeLogin(){
    this.login= true;
  }
}
