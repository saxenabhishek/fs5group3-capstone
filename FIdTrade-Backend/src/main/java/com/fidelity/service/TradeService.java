package com.fidelity.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelity.business.Direction;
import com.fidelity.business.Instrument;
import com.fidelity.business.Order;
import com.fidelity.business.Price;
import com.fidelity.business.Trade;
import com.fidelity.integration.PortfolioDaoImpl;
import com.fidelity.integration.TradeDao;
import com.fidelity.shared.Helper;

@Service
public final class TradeService {

	@Autowired
	Logger logger;

	@Autowired
	TradeDao tradeDao;

	@Autowired
	PortfolioDaoImpl portfolioDaoImpl;

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

		List<Trade> portfolio =  portfolioDaoImpl.getHoldings(order.getClientId());

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
}
