package com.fidelity.integration;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.fidelity.business.Trade;

public interface PortfolioMapper {
	@Select("select id from ft_client where id= #{value}")
	String getClientExistence(String clientId);	
	
	List<Trade> getPortfolioTrades(String clientId);
	List<Trade> getHoldings(String clientId);
}
