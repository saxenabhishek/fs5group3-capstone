package com.fidelity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.fidelity.business.Trade;

@Mapper
public interface PortfolioMapper {
	@Select("select id from ft_client where id= #{value}")
	String getClientExistence(String clientId);	
	
	List<Trade> getPortfolioTrades(String clientId);
	List<Trade> getHoldings(String clientId);
}
