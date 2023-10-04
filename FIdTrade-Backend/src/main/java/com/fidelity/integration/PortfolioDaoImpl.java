package com.fidelity.integration;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fidelity.business.Trade;
import com.fidelity.mapper.PortfolioMapper;

@Repository("portfolioDao")
public class PortfolioDaoImpl {
	@Autowired 
	private Logger logger;

	@Autowired
	private PortfolioMapper portfolioMapper;
	
	public List<Trade> getPortfolioTrades(String clientId) {
		if (clientId == null)
			throw new NullPointerException("Client Id can't be null");
		
		if (portfolioMapper.getClientExistence(clientId) != null)
			return portfolioMapper.getPortfolioTrades(clientId);		
		else 
			throw new NullPointerException("Client doesn't exist");
	}
	
	public List<Trade> getHoldings(String clientId) {
		if (clientId == null)
			throw new NullPointerException("Client Id can't be null");
		
		if (portfolioMapper.getClientExistence(clientId) != null)
			return portfolioMapper.getHoldings(clientId);		
		else 
			throw new NullPointerException("Client doesn't exist");
	}
}
