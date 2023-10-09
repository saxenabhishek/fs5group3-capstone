package com.fidelity.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelity.business.Direction;
import com.fidelity.business.IncomeCategory;
import com.fidelity.business.Instrument;
import com.fidelity.business.LengthOfInvestment;
import com.fidelity.business.Order;
import com.fidelity.business.Preference;
import com.fidelity.business.Price;
import com.fidelity.business.RiskTolerance;
import com.fidelity.business.RoboTrade;
import com.fidelity.business.Trade;
import com.fidelity.integration.PreferenceDaoMyBatisImpl;
import com.fidelity.integration.TradeDao;
import com.fidelity.shared.Helper;

@Service
public final class TradeService {
	@Autowired
	PortfolioService portfolioService;
	
	@Autowired
	TradeDao tradeDao;
    @Autowired
    PreferenceDaoMyBatisImpl prefDao;
	List<Price> instrumentPrices = new ArrayList<>();

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
		double currentBalance = 1000000;

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

		if (cashValue.compareTo(new BigDecimal(currentBalance)) > 0) {
			throw new RuntimeException("Can't execute trade, not enough balance");
		}

		// Reduce currentBalance and update clientBalance

		Trade trade = new Trade(order, cashValue, "123", Instant.now());

		
		tradeDao.addTrade(trade);
		
		return trade;

	}

	public Trade postSellTrade(Order order){

		// accept list of portfolio objects of current client

		BigDecimal execPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
		Trade trade;

		List<Trade> portfolio =  portfolioService.retrieveCurrentHoldings(order.getClientId());

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
						"123",
						Instant.now());

				tradeDao.addTrade(trade);

				// update user balance

				return trade;

			} else {
				throw new RuntimeException("Sell couldn't get executed, Invalid bid price");
			}
		} else {
			throw new NullPointerException("Bad portfolio list");
		}

	}

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
	        List<Preference> clientPreference = prefDao.getAllPreference();
	        List<Trade> availableTrades = tradeDao.getRoboSuggestion(clientId);

	        List<Trade> filteredTrades = filterTradesByPreference(clientPreference, availableTrades);
	        List<Trade> sortedBuyTrades = rankBuyTrades(filteredTrades);

	        return sortedBuyTrades.stream()
	                .limit(5)
	                .collect(Collectors.toList());
	    }

	    private List<Trade> filterTradesByPreference(List<Preference> clientPreference, List<Trade> availableTrades) {
	        List<Trade> filteredTrades = availableTrades.stream()
	                .filter(trade -> {
	                    if (((Preference) clientPreference).getRiskTolerance() == RiskTolerance.AGGRESSIVE || ((Preference) clientPreference).getRiskTolerance() == RiskTolerance.ABOVE_AVERAGE) {
	                        return isHighRiskTrade(trade);
	                    } else if (((Preference) clientPreference).getRiskTolerance() == RiskTolerance.AVERAGE || ((Preference) clientPreference).getRiskTolerance() == RiskTolerance.CONSERVATIVE) {
	                        return isModerateOrHighRiskTrade(trade);
	                    } else if (((Preference) clientPreference).getRiskTolerance() == RiskTolerance.BELOW_AVERAGE) {
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


public List<RoboTrade> roboAdvisor(Preference p){
	
//	if(p.getIsChecked()=="T") 
//	{
	
	if(p.getIncomeCategory() == IncomeCategory.ZeroToTwentyK && p.getRiskTolerance()==RiskTolerance.ABOVE_AVERAGE)
	{
		List<RoboTrade> topStocks = new ArrayList<>();
		topStocks.add(new RoboTrade("Amazon","AMZ",11,70000,"Stock",20,Direction.BUY,Direction.SELL));
		return topStocks;
	}
	else if(p.getLengthOfInvestment()==LengthOfInvestment.ZeroToFiveYears && p.getIncomeCategory()==IncomeCategory.TwentyKToFortyK)
	{
		List<RoboTrade> topStocks = new ArrayList<>();
		topStocks.add(new RoboTrade("Apple","APPL",2,80000,"ETF",20,Direction.BUY,Direction.SELL));
		topStocks.add(new RoboTrade("Amazon","AMZ",11,70000,"Stock",20,Direction.BUY,Direction.SELL));
		return topStocks;	
	}
	else if (p.getIncomeCategory()==IncomeCategory.OneLToOneLFifty&&p.getRiskTolerance()==RiskTolerance.AGGRESSIVE)
	{
		List<RoboTrade> topStocks = new ArrayList<>();
		topStocks.add(new RoboTrade("Apple","APPL",2,80000,"ETF",20,Direction.BUY,Direction.SELL));
		topStocks.add(new RoboTrade("Paypal","PAY",12,50000,"Mutual Fund",20,Direction.BUY,Direction.SELL));
		topStocks.add(new RoboTrade("Microsoft","MSFT",9,30000,"Stock",20,Direction.BUY,Direction.SELL));
		return topStocks;	
	}
//	}
	return null;
}
}
	
	
	
//	public List<Trade> findRoboSuggest(String clientId){
//		List<Trade> topStocks =tradeDao.getRoboSuggestion(clientId);
//		List<Preference> clientPreference = prefDao.getAllPreference();
//		System.out.println(clientPreference);
//		List<Trade> availableTrades = new ArrayList<>();
//		List<Trade> filteredTrades = new ArrayList<>();
//		BigDecimal comparisonValue = new BigDecimal("1000.0");
//	}
	//	for (Trade trade : topStocks) {
		    // Apply filters based on client's preferences
//		    if (isPreferredInvestmentLength(trade, clientPreference) && isAcceptableRisk(trade, clientPreference)) {
//		        filteredTrades.add(trade);
//		    }
		
	//	}
//		 List<Trade> filteredTrades = filterTradesByPreference();
//		List
//		public List<Preference> getAllPreference() {
//			logger.debug("enter");
//			return preferenceMapper.getAllPreference();
//		}
		
//	
//
//		    public List<Trade> getTopBuyTrades(List<Preference> clientp) {
//
//		        List<Trade> filteredTrades = filterTradesByPreference(client);
//		       // System.out.println(clientp.);
//
//
//		        List<Trade> sortedBuyTrades = rankBuyTrades(filteredTrades);
//
//		        System.out.println(sortedBuyTrades.size());
//
//		        return sortedBuyTrades.stream()
//
//		                .limit(5)
//
//		                .collect(Collectors.toList());
//
//		    }
//
//		    private List<Trade> filterTradesByPreference(Preference p) {
//
//		        List<Trade> filteredTrades = new ArrayList<>();
//		        List<Trade> availableTrades = new ArrayList<>();
//                System.out.println(availableTrades.size());
//		        for (Trade trade : availableTrades) {
//
//		        	System.out.println(p);
//		            if (p.getRiskTolerance()==RiskTolerance.AGGRESSIVE||p.getRiskTolerance()==RiskTolerance.ABOVE_AVERAGE) {
//		                if (isHigRiskTrade(trade)) {
//
//		                    filteredTrades.add(trade);
//
//		                }
//
//		            } else if (p.getRiskTolerance() == RiskTolerance.AVERAGE||p.getRiskTolerance() == RiskTolerance.CONSERVATIVE) {
//
//		                if (isModerateOrHighRiskTrade(trade)) {
//
//		                    filteredTrades.add(trade);
//
//		                }
//
//		            } 
//		            else if ((p.getRiskTolerance() == RiskTolerance.BELOW_AVERAGE))
//		            {
//		                filteredTrades.add(trade);
//
//		            }
//
//		        }
//		        return filteredTrades;
//
//		    }
//
//			private boolean isModerateOrHighRiskTrade(Trade trade) {
//				BigDecimal comparisonValue = new BigDecimal("500.0");
//		        return trade.getCashValue().compareTo(comparisonValue)>0 ;
//				
//			}
//
//			private boolean isHigRiskTrade(Trade trade) {
//				System.out.println(trade.getCashValue());
//				BigDecimal comparisonValue = new BigDecimal("1000.0");
//		        return trade.getCashValue().compareTo(comparisonValue)>0 ;
//			}
//			
//
//			private List<Trade> rankBuyTrades(List<Trade> trades) {
//
//		        return trades.stream()
//
//		                .filter(trade -> trade.getDirection()== Direction.BUY)
//		                .sorted(Comparator.comparing(Trade::getCashValue, Comparator.reverseOrder()))
//		                .collect(Collectors.toList());
//
//		    }

		 
//
//		    private List<Trade> rankSellTrades(List<Trade> trades) {
//
//		    	for (Trade trade2 : trades) {
//					System.out.println(trade2.getType());
//				}
////		    	System.out.println(trades.stream().filter(trade -> {System.out.println(trade.getType()); return true;}));
//
//		    	  return trades.stream()
//
//		                  .filter(trade -> trade.getType() == TradeType.SELL)
//
//		                  .sorted(Comparator.comparingDouble(Trade::getAmount).reversed())
//
//		                  .collect(Collectors.toList());
//		    
//		}
	
//	}


	
