package com.fidelity.dao;

import java.util.List;

import com.fidelity.models.Trade;

public interface PortfolioDao {
	List<Trade> getPortfolio(String clientId);
}
