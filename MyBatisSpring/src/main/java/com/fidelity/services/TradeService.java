package com.fidelity.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fidelity.business.Direction;
import com.fidelity.business.Instrument;
import com.fidelity.business.Order;
import com.fidelity.business.Portfolio;
import com.fidelity.business.Price;
import com.fidelity.business.Trade;
import com.fidelity.shared.Helper;

public final class TradeService {
	List<Price> instrumentPrices = new ArrayList<>();

	// common portfolio for all users for now
	List<Portfolio> portfolio = new ArrayList<>();

	BigDecimal tolerance = Helper.makeBigDecimal("0.05");
	double fee = 0.01;

	public TradeService() {
		Instrument instrOne = new Instrument("JP Morgan", "A123", 100, "N123", 1, "STOCK");
		Instrument instrTwo = new Instrument("Apple", "A234", 100, "N234", 1, "STOCK");

		Price jpmcPrice = new Price(instrOne, Helper.makeBigDecimal("101"), "N123", Helper.makeBigDecimal("104.25"),
				LocalDateTime.now());
		Price appPrice = new Price(instrTwo, Helper.makeBigDecimal("105"), "N234", Helper.makeBigDecimal("110"),
				LocalDateTime.now());

		instrumentPrices.add(jpmcPrice);
		instrumentPrices.add(appPrice);

		// todo: In production call a service to put all prices in the instrumentPrices
	}

	public List<Price> getAllInsturments() {
		return instrumentPrices;
	}

	public Price getPriceByInstrumentId(String id) {
		for (Price p : instrumentPrices) {
			if (p.getInstrumentId().equals(id)) {
				return p;
			}
		}
		throw new NullPointerException("Invalid Instrument ID");
	}

	public Trade postBuyTrade(Order order) throws Exception {
		// Trade trade = new Trade(order, order.getTargetPrice(), "", Instant.now());

		BigDecimal execPrice;
		
		// get balance from client
		double currentBalance = 1000.00;

		if (instrumentPrices == null && order.getDirection() != Direction.BUY) {
			throw new NullPointerException("Bad instrument Prices");
		}

		Price instrPrice = getPriceByInstrumentId(order.getInstrumentId());

		BigDecimal targetPrice = order.getTargetPrice();
		BigDecimal askPrice = instrPrice.getAskPrice();

		BigDecimal lowerBound = askPrice.multiply(BigDecimal.ONE.subtract(tolerance));
		BigDecimal upperBound = askPrice.multiply(BigDecimal.ONE.add(tolerance));

		if (targetPrice.compareTo(lowerBound) < 0 || targetPrice.compareTo(upperBound) > 0) {
			throw new IllegalArgumentException("Buy couldn't get executed, Invalid ask price");
		}

		execPrice = askPrice;

		double cashValuePre = order.getQuantity() * (1.0 + fee);
		BigDecimal cashValue = new BigDecimal(cashValuePre).multiply(execPrice);

		if (cashValue.compareTo(new BigDecimal(currentBalance)) > 0) {
			throw new Exception("Can't execute trade, not enough balance");
		}

		// Reduce currentBalance and update clientBalance

		Trade trade = new Trade(order, cashValue, "123", Instant.now());
		
		portfolio.add(new Portfolio(order.getInstrumentId(), instrPrice.instrument.getDescription(), execPrice, trade.getQuantity() , trade.getClientId()));

		return trade;

	}

	public Trade postSellTrade(Order order) throws Exception {

		// accept list of portfolio objects of current client

		BigDecimal execPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
		Trade trade;
		double currentBalance = 1000.00;

		if (portfolio != null) {
			for (Portfolio p : portfolio) {
				if (p.getInstrumentId().equals(order.getInstrumentId())) {

					if (p.getQuantity() < order.getQuantity()) {
						throw new NullPointerException("Can't sell more than you have");
					}


					BigDecimal targetPrice = order.getTargetPrice();
					Price currentPrice = getPriceByInstrumentId(p.getInstrumentId());

					BigDecimal lowerBound = currentPrice.getBidPrice().multiply(BigDecimal.ONE.subtract(tolerance));
					BigDecimal upperBound = currentPrice.getBidPrice().multiply(BigDecimal.ONE.add(tolerance));


					if (targetPrice.compareTo(lowerBound) < 0 || targetPrice.compareTo(upperBound) > 0) {
						throw new Exception("Sell couldn't get executed, Invalid bid price");
					}

					execPrice = currentPrice.getBidPrice();

				}
			}

			System.out.println(execPrice);
			
			if (execPrice.compareTo(BigDecimal.ZERO) > 0) {
				double cashValuePre = order.getQuantity() * (1.0 + fee);
				BigDecimal cashValue = new BigDecimal(cashValuePre).multiply(execPrice).setScale(2,
						RoundingMode.HALF_DOWN);

				trade = new Trade(
						order,
						cashValue,
						"123",
						Instant.now());

				// trade = new Trade(order, cashValue, order.getQuantity(), "S",
				// order.getInstrumentId(), order.getClientId(), "XYZ", execPrice );
				// c.setBalance(currentBalance + cashValue.doubleValue());
				// to update portfolio
				for (Portfolio inst : portfolio) {
					if (inst.getInstrumentId().equals(order.getInstrumentId())) {
						if(inst.getQuantity() < order.getQuantity()) {
							throw new Exception("Can not sell more that owned amount");
						}
						else if(inst.getQuantity() == order.getQuantity()) {
							portfolio.remove(inst);
						}
						else {
							inst.setQuantity(inst.getQuantity() - order.getQuantity());
						}
					}
				}

				return trade;

			} else {
				throw new Exception("Sell couldn't get executed, Invalid bid price");
			}
		} else {
			throw new NullPointerException("Bad portfolio list");
		}

	}

	public static void getClientProtfolio() {

	}

}
