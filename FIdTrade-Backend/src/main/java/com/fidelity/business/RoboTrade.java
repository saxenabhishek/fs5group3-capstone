package com.fidelity.business;

public class RoboTrade {
	
	private String securityName;
	private String stockSymbol;
	private int tradeId;
	private int tradePrice;
	private String tradeType;
	private int tradeFluctuation;
	private Direction buy, sell;

	
	public RoboTrade(String securityName,String stockSymbol,int tradeId,int tradePrice,String tradeType,int tradeFluctuation,Direction buy,Direction sell){
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
	public Direction getbuy() {
		return buy;
	}
	public Direction getsell() {
		return sell;
	}
	public int gettradePrice()
	{
		return tradePrice;
	}

}
