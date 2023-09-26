package com.fidelity.integration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fidelity.business.Trade;

@Repository("portfolioDao")
public class PortfolioDaoImpl {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PortfolioMapper portfolioMapper;
	
	public List<Trade> getPortfolio(String clientId) {
		if (clientId == null)
			throw new NullPointerException("Client Id can't be null");
		
		if (portfolioMapper.getClientExistence(clientId) != null)
			return portfolioMapper.getPortfolio(clientId);		
		else 
			throw new NullPointerException("Client doesn't exist");
	}
}
