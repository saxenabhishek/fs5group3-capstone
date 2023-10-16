package com.fidelity.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Client;
import com.fidelity.business.Direction;
import com.fidelity.business.Instrument;
import com.fidelity.business.Order;
import com.fidelity.business.Preference;
import com.fidelity.business.Price;
import com.fidelity.business.RiskTolerance;
import com.fidelity.business.Trade;
import com.fidelity.integration.ClientDao;
import com.fidelity.integration.PortfolioDaoImpl;
import com.fidelity.integration.TradeDao;
import com.fidelity.shared.Helper;

@Service
public class TradeService {

	@Autowired
	Logger logger;

	@Autowired
	TradeDao tradeDao;

	@Autowired
	PortfolioDaoImpl portfolioDaoImpl;

	@Autowired
	PortfolioService portfolioService;

//	@Autowired
//	PreferenceDaoMyBatisImpl prefDao;

	@Autowired
	ClientDao clientDao;
	
	List<Price> instrumentPrices;

	BigDecimal tolerance = Helper.makeBigDecimal("0.05");
	BigDecimal fee = Helper.makeBigDecimal("0.1");
	
	public TradeService() {
		
	}

	public List<Instrument> getAllInstruments(String InstrumentId) {
		if (InstrumentId.isBlank()){
			
			return tradeDao.getAllInstruments();
		}
		return tradeDao.getInstrumentById(InstrumentId);
	}

	public List<Price> getAllPrices(String PriceId){
		if (PriceId.isBlank()){
			return tradeDao.getAllPrices();
		}
		return tradeDao.getPricesById(PriceId);
	}

	public Price getPriceByInstrumentId(String id) {
		// use the get all Instrument method instead
		for (Price p : instrumentPrices) {
			if (p.getInstrumentId().equals(id)) {
				return p;
			}
		}
		throw new RuntimeException("Invalid Instrument ID");
	}

	public Trade postBuyTrade(Order order) {
		BigDecimal execPrice;
		
		// get balance from client
		String clientID = order.getClientId();
		if(clientID == null){
			throw new RuntimeException("ClientId can not be null");
		}
		Client client = clientDao.getClientsByID(order.getClientId());

		BigDecimal currentBalance = client.getWalletBalance();

		instrumentPrices = getAllPrices("");
		if (instrumentPrices == null && order.getDirection() != Direction.BUY) {
			throw new RuntimeException("Bad instrument Prices");
		}

		// Most recent price of instrument
		// todo: directly query this from the db
		Price instrPrice = getPriceByInstrumentId(order.getInstrumentId());

		BigDecimal targetPrice = order.getTargetPrice();
		BigDecimal askPrice = instrPrice.getAskPrice();

		BigDecimal lowerBound = askPrice.multiply(BigDecimal.ONE.subtract(tolerance));
		BigDecimal upperBound = askPrice.multiply(BigDecimal.ONE.add(tolerance));

		if (targetPrice.compareTo(lowerBound) < 0 || targetPrice.compareTo(upperBound) > 0) {
			throw new RuntimeException("Buy couldn't get executed, Invalid ask price");
		}

		// will be true in most cases
		execPrice = askPrice; 

		// cashValue = (quantity * execPrice) * (1 + fee)
		BigDecimal cashValue = new BigDecimal(order.getQuantity()).multiply(execPrice).multiply(BigDecimal.ONE.add(fee));

		logger.debug(cashValue.toPlainString());
		logger.debug(currentBalance.toPlainString());

		System.out.println(cashValue.toPlainString() + " the next one is current balance" +  currentBalance.toString());

		if (cashValue.compareTo(currentBalance) > 0) {
			throw new RuntimeException("Can't execute trade, not enough balance");
		}


		clientDao.updateClientWalletBalance(order.getClientId(), currentBalance.subtract(cashValue));

		Trade trade = new Trade(order, cashValue, "TID" + Helper.generateRandomNumericString(3), Instant.now());
		tradeDao.addTrade(trade);

		
		return trade;

	}

	public Trade postSellTrade(Order order){

		// accept list of portfolio objects of current client

		BigDecimal execPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
		Trade trade;

		Client client = clientDao.getClientsByID(order.getClientId());

		BigDecimal currentBalance = client.getWalletBalance();

		List<Trade> portfolio =  portfolioService.retrieveCurrentHoldings(order.getClientId());

		instrumentPrices = getAllPrices("");

		if (portfolio != null) {
			for (Trade p : portfolio) {
				if (p.getInstrumentId().equals(order.getInstrumentId())) {

					if (p.getQuantity() < order.getQuantity()) {
						throw new NullPointerException("Can't sell more than you have");
					}


					BigDecimal targetPrice = order.getTargetPrice();
					Price currentPrice = getPriceByInstrumentId(p.getInstrumentId());

					BigDecimal lowerBound = currentPrice.getBidPrice().multiply(BigDecimal.ONE.subtract(tolerance));
					BigDecimal upperBound = currentPrice.getBidPrice().multiply(BigDecimal.ONE.add(tolerance));


					if (targetPrice.compareTo(lowerBound) < 0 || targetPrice.compareTo(upperBound) > 0) {
						throw new RuntimeException("Sell couldn't get executed, Invalid bid price");
					}

					execPrice = currentPrice.getBidPrice();

				}
			}

			System.out.println(execPrice);
			
			if (execPrice.compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal cashValue = new BigDecimal(order.getQuantity()).multiply(execPrice).multiply(BigDecimal.ONE.add(fee));

				trade = new Trade(
						order,
						cashValue,
						"TID" + Helper.generateRandomNumericString(3),
						Instant.now());

				tradeDao.addTrade(trade);

				// update user balance
				clientDao.updateClientWalletBalance(order.getClientId(), currentBalance.add(cashValue));

				return trade;

			} else {
				throw new RuntimeException("Sell couldn't get executed, Invalid bid price");
			}
		} else {
			throw new NullPointerException("Bad portfolio list");
		}

	}

	@Transactional
	public Trade processOrder(Order order) {
		tradeDao.addOrder(order);
		if (order.getDirection() == Direction.BUY){
			return postBuyTrade(order);
		}
		else{
			return postSellTrade(order);
		}
	}

	   public List<Trade> getTopBuyTrades(String clientId) {
	        Preference clientPreference = clientDao.queryForPreferenceById(clientId);
	        List<Trade> availableTrades = tradeDao.getRoboSuggestion(clientId);

	        List<Trade> filteredTrades = filterTradesByPreference(clientPreference, availableTrades);
	        List<Trade> sortedBuyTrades = rankBuyTrades(filteredTrades);

	        return sortedBuyTrades.stream()
	                .limit(5)
	                .collect(Collectors.toList());
	    }
	   public List<Trade> getTopSellTrades(String clientId) {
		   	Preference clientPreference = clientDao.queryForPreferenceById(clientId);
	        List<Trade> availableTrades = tradeDao.getRoboSuggestion(clientId);
	        List<Trade> filteredTrades = filterTradesByPreference(clientPreference, availableTrades);
	        List<Trade> sortedSellTrades = rankSellTrades(filteredTrades);
                    return sortedSellTrades.stream()

	                .limit(5)

	                .collect(Collectors.toList());

	    }

	    private List<Trade> filterTradesByPreference(Preference clientPreference, List<Trade> availableTrades) {
	        List<Trade> filteredTrades = availableTrades.stream()
	                .filter(trade -> {
	                    if (clientPreference.getRiskTolerance() == RiskTolerance.AGGRESSIVE || ( clientPreference).getRiskTolerance() == RiskTolerance.ABOVE_AVERAGE) {
	                        return isHighRiskTrade(trade);
	                    } else if (clientPreference.getRiskTolerance() == RiskTolerance.AVERAGE || ((Preference) clientPreference).getRiskTolerance() == RiskTolerance.CONSERVATIVE) {
	                        return isModerateOrHighRiskTrade(trade);
	                    } else if (clientPreference.getRiskTolerance() == RiskTolerance.BELOW_AVERAGE) {
	                        return true;
	                    }
	                    return false;
	                })
	                .collect(Collectors.toList());

	        return filteredTrades;
	    }

	    private boolean isModerateOrHighRiskTrade(Trade trade) {
	        BigDecimal comparisonValue = new BigDecimal("500.0");
	        return trade.getCashValue().compareTo(comparisonValue) > 0;
	    }

	    private boolean isHighRiskTrade(Trade trade) {
	        BigDecimal comparisonValue = new BigDecimal("1000.0");
	        return trade.getCashValue().compareTo(comparisonValue) > 0;
	    }

	    private List<Trade> rankBuyTrades(List<Trade> trades) {
	        return trades.stream()
	                .filter(trade -> trade.getDirection() == Direction.BUY)
	                .sorted(Comparator.comparing(Trade::getCashValue, Comparator.reverseOrder()))
	                .collect(Collectors.toList());
	    }
	    private List<Trade> rankSellTrades(List<Trade> trades) {
	        return trades.stream()
	                .filter(trade -> trade.getDirection() == Direction.SELL)
	                .sorted(Comparator.comparing(Trade::getCashValue, Comparator.reverseOrder()))
	                .collect(Collectors.toList());
	    }
	    


}
	
	
	


	
