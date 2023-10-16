package com.fidelity.service;

import java.util.List;

import com.fidelity.business.Order;
import com.fidelity.business.Price;
import com.fidelity.business.Trade;
import com.fidelity.controller.dto.WalletDTO;

public interface PortfolioService {
	List<Trade> retrieveAllPortfolioTrades(String clientId);
	List<Trade> retrieveCurrentHoldings(String clientId);
	List<Order> retrieveTradeHistory(String clientId);
	List<Price> retrieveCurrentInstrumentPrices(String category, String instrumentId);
	WalletDTO retrieveWalletBalance(String clientId);
}
