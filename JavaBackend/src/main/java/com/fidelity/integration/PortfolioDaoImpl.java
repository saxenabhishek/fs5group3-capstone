package com.fidelity.integration;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.models.Trade;

@Repository
public class PortfolioDaoImpl {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PortfolioDao portfolioDao;
	
	public List<Trade> getPortfolio(String clientId) {
		return portfolioDao.getPortfolio(clientId);
		
	}
}
