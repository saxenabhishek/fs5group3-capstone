package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.Trade;

public interface PortfolioMapper {
	List<Trade> getPortfolio(String clientId);
}
