package com.fidelity.services;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fidelity.business.Direction;
import com.fidelity.business.Instrument;
import com.fidelity.business.Order;
import com.fidelity.business.Price;
import com.fidelity.business.Trade;

@Service
public class PortfolioService {	
	private List<Order> tradeHistory;
	private List<Trade> portfolio;
	private List<Instrument> instruments;
	private List<Price> prices;
	
	public PortfolioService() {
		this.tradeHistory = new ArrayList<Order>();
		this.portfolio= new ArrayList<Trade>();
		this.instruments= new ArrayList<Instrument>();
		this.prices= new ArrayList<Price>();
	}
	
	public void addTrade(Order o) {
		if (o == null)
			throw new NullPointerException ("Order can't be null");
		
		this.tradeHistory.add(o);
		if (o.getDirection() == Direction.BUY)
			this.portfolio.add(new Trade(o, o.getTargetPrice() , "Trade00" + this.getPortfolioLength()+1, Instant.now()));
	}

	public int getTradeHistoryLength() {
		return this.tradeHistory.size();
	}
	
	public int getPortfolioLength() {
		return this.portfolio.size();
	}
	
	public int getInstrumentsLength() {
		return this.instruments.size();
	}
	
	public int getPricesLength() {
		return this.prices.size();
	}
	
	public List<Order> getTradeHistoryOnly100(){
		if (this.getTradeHistoryLength() == 0)
			return this.tradeHistory;
		
		return tradeHistory.stream()
				.sorted(Comparator.comparing(Order::getOrderTimestamp).reversed())
				.limit(100)
				.collect(Collectors.toList());
	}
	
	public List<Order> getTradeHistory(){
		return this.tradeHistory;
	}
	
	public List<Trade> getPortfolio(){
		return this.portfolio;
	}
	
	public List<Instrument> getInstruments() {
		return this.instruments;
	}
	
	public Instrument getInstrumentById(String id) {
		for (Instrument i: this.instruments) 
			if (id.equals(i.getInstrumentId()))
				return i;
		
		throw new NullPointerException ("Instrument with " + id + " doesn't exist !");
	}
	
	public List<Price> getPrices() {
		return this.prices;
	}
	
	public Price getPriceById(String id) {
		for (Price p: this.prices) 
			if (id.equals(p.getInstrument().getInstrumentId()))
				return p;
		
		throw new NullPointerException ("Prices of instrument with " + id + " doesn't exist !");
	}

	@Override
	public int hashCode() {
		return Objects.hash(instruments, portfolio, prices, tradeHistory);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PortfolioService other = (PortfolioService) obj;
		return Objects.equals(instruments, other.instruments) && Objects.equals(portfolio, other.portfolio)
				&& Objects.equals(prices, other.prices) && Objects.equals(tradeHistory, other.tradeHistory);
	}	
}
