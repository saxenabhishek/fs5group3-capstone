package com.fidelity.integration;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.business.Order;
import com.fidelity.business.Trade;
import com.fidelity.controller.dto.WalletDTO;
import com.fidelity.integration.mapper.PortfolioMapper;

@Repository("portfolioDao")
public class PortfolioDaoImpl implements PortfolioDao {
	@Autowired 
	private Logger logger;

	@Autowired
	private PortfolioMapper portfolioMapper;
	
	@Override
	public List<Trade> getPortfolioTrades(String clientId) {
		if (clientId == null)
			throw new NullPointerException("Client Id can't be null");
		
		logger.debug("GET all portfolio trades");		
		if (portfolioMapper.getClientExistence(clientId) != null)
			return portfolioMapper.getPortfolioTrades(clientId);		
		else 
			throw new NullPointerException("Client doesn't exist");
	}
	
	@Override
	public List<Trade> getHoldings(String clientId) {
		if (clientId == null)
			throw new NullPointerException("Client Id can't be null");
		
		logger.debug("GET all current portfolio holdings");
		if (portfolioMapper.getClientExistence(clientId) != null)
			return portfolioMapper.getHoldings(clientId);		
		else 
			throw new NullPointerException("Client doesn't exist");
	}
	
	@Override
	public List<Order> getAllTradeHistory(String clientId) {
		logger.debug("GET all trade history");
        List<Order> tradeHistory= portfolioMapper.getAllTradeHistory(clientId);
        return tradeHistory;
	}
	
	@Override
	public WalletDTO getWalletBalance(String clientId) {
		return portfolioMapper.getWalletBalance(clientId);
	}
}
