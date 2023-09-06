export class MockRoboadvisor {
    
    constructor(
        public securityName:string,
        public stockSymbol:string,
        public tradeId:number,
        public tradePrice:number,
        public tradeType:string,
        public tradeQuantity:number,
        public tradeDate:string,
        public buy:string,
        public sell:string
        
    ) {
    
    }
  }