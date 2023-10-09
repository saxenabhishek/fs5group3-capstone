package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.Order;
import com.fidelity.business.Trade;

public interface PortfolioDao {
	List<Trade> getPortfolioTrades(String clientId);
	List<Trade> getHoldings(String clientId);	
	List<Order> getAllTradeHistory(String clientId);
}
