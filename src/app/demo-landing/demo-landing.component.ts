import { Component } from '@angular/core';

@Component({
  selector: 'app-demo-landing',
  templateUrl: './demo-landing.component.html',
  styleUrls: ['./demo-landing.component.css']
})
export class DemoLandingComponent {
  featuredAssets: any[] = [
    {
      name: 'Company A Stocks',
      description: 'Invest in the stocks of Company A for great returns.',
      imageUrl: 'assets/company-a.jpg' // Replace with your image URL
    },
    {
      name: 'Bitcoin',
      description: 'Buy and trade Bitcoin, the world\'s most popular cryptocurrency.',
      imageUrl: 'assets/bitcoin.jpg' // Replace with your image URL
    },
    // Add more featured assets here
  ];
}
