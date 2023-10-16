package com.fidelity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Order;
import com.fidelity.business.Price;
import com.fidelity.business.Trade;
import com.fidelity.controller.dto.WalletDTO;
import com.fidelity.integration.FMTSDao;
import com.fidelity.integration.PortfolioDao;

@Service
@Transactional
public class PortfolioServiceImpl implements PortfolioService {	
	
	@Autowired
	PortfolioDao portfolioDao;
	
	@Autowired
	FMTSDao fmtsDao;	
	
	@Override
	public List<Trade> retrieveCurrentHoldings(String clientId){
		List<Trade> allHoldings;
		try {
			allHoldings= portfolioDao.getHoldings(clientId);
		}
		catch (Exception e) {
			String msg = "Error querying current holdings in the FidTrade database.";
			throw new FidTradeDatabaseException(msg, e);
		}
		return allHoldings;
	}
	
	@Override
	public List<Trade> retrieveAllPortfolioTrades(String clientId){
		List<Trade> allTrades;
		try {
			allTrades= portfolioDao.getPortfolioTrades(clientId);
		}
		catch (Exception e) {
			String msg = "Error querying all portfolio trades in the FidTrade database.";
			throw new FidTradeDatabaseException(msg, e);
		}
		return allTrades;
	}
	
	@Override
	public List<Order> retrieveTradeHistory(String clientId){
		List<Order> allTradeHistory;
		try {
			allTradeHistory= portfolioDao.getAllTradeHistory(clientId);
		}
		catch (Exception e) {
			String msg = "Error querying trade history in the FidTrade database.";
			throw new FidTradeDatabaseException(msg, e);
		}
		return allTradeHistory;
	}
	
	@Override
	public List<Price> retrieveCurrentInstrumentPrices(String category, String instrumentId){
		List<Price> currentPrices;
		try {
			currentPrices= fmtsDao.getCurrentPricesFromFMTS(category, instrumentId);
		}
		catch (Exception e) {
			String msg = "Error querying for current prices from the FMTS database.";
			throw new FidTradeDatabaseException(msg, e);
		}
		return currentPrices;
	}
	
	@Override
	public WalletDTO retrieveWalletBalance(String clientId) {
		try {
			return portfolioDao.getWalletBalance(clientId);
		}
		catch (Exception e) {
			String msg = "Error querying for wallet balance in the FidTrade database.";
			throw new FidTradeDatabaseException(msg, e);
		}
	}
}
