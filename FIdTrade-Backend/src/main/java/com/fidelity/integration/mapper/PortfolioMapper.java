package com.fidelity.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fidelity.business.Order;
import com.fidelity.business.Trade;
import com.fidelity.controller.dto.WalletDTO;

@Mapper
public interface PortfolioMapper {
	String getClientExistence(@Param("clientId") String clientId);		
	List<Trade> getPortfolioTrades(@Param("clientId") String clientId);
	List<Trade> getHoldings(@Param("clientId") String clientId);	
	List<Order> getAllTradeHistory(@Param("clientId") String clientId);
	WalletDTO getWalletBalance(@Param("clientId") String clientId);
}
