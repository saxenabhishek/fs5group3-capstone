package services;

import java.util.*;
import java.util.stream.Collectors;

import models.Trade;

public class PortfolioService {	
	private List<Trade> tradeHistory;
	
	public PortfolioService() {
		this.tradeHistory = new ArrayList<Trade>();
	}

	public void addTrade(Trade t) {
		if(t instanceof Trade) 
			this.tradeHistory.add(t);		
		else 
			throw new IllegalArgumentException("Invalid trade object");
	}

	public int getTradeHistoryLength() {
		return this.tradeHistory.size();
	}
	
	public List<Trade> getTradeHistory(){
		return tradeHistory.stream()
				.sorted(Comparator.comparing(Trade::getTimestamp).reversed())
				.limit(100)
				.collect(Collectors.toList());
	}
}
