package models;

public class RoboTrade {
	
	private String securityName;
	private String stockSymbol;
	private int tradeId;
	private int tradePrice;
	private String tradeType;
	private int tradeFluctuation;
	private String buy;
	private String sell;

	
	public RoboTrade(String securityName,String stockSymbol,int tradeId,int tradePrice,String tradeType,int tradeFluctuation,String buy,String sell){
		 this.securityName =securityName;
		 this.stockSymbol =stockSymbol;
		 this.tradeId =tradeId;
		 this.tradePrice=tradePrice;
		 this.tradeType =tradeType;
		 this.tradeFluctuation =tradeFluctuation;
		 this.buy =buy;
		 this.sell=sell;

}
	public String getSecurityName() {
		return securityName;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public int getTradeId() {
		return tradeId;
	}
	public String gettradeType() {
		return tradeType;
	}
	public int gettradeFluctuation() {
		return tradeFluctuation;
	}
	public String getbuy() {
		return buy;
	}
	public String getsell() {
		return sell;
	}
	public int gettradePrice()
	{
		return tradePrice;
	}

}
