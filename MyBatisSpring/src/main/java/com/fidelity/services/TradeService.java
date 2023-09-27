package com.fidelity.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelity.business.Direction;
import com.fidelity.business.Instrument;
import com.fidelity.business.Order;

import com.fidelity.business.Price;
import com.fidelity.business.Trade;
import com.fidelity.integration.TradeDao;
import com.fidelity.shared.Helper;

@Service
public final class TradeService {
	@Autowired
	PortfolioService portfolioService;
	
	@Autowired
	TradeDao tradeDao;

	List<Price> instrumentPrices = new ArrayList<>();

	BigDecimal tolerance = Helper.makeBigDecimal("0.05");
	BigDecimal fee = Helper.makeBigDecimal("0.1");
	
	public TradeService() {
		instrumentPrices = new ArrayList<>();
		Instrument i1 = new Instrument("JP Morgan", "A123", 100, "N123", 1, "STOCK");
		Price p1 = new Price(i1,  Helper.makeBigDecimal("104.00") , "N123", Helper.makeBigDecimal("104.00"), LocalDate.now());
		instrumentPrices.add(p1);


		Instrument i2 = new Instrument("Apple", "A234", 100, "ID1232", 1, "STOCK");
		Price p2 =  new Price(i1,  Helper.makeBigDecimal("104.00") , "N123", Helper.makeBigDecimal("104.00"), LocalDate.now());
		instrumentPrices.add(p2);

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
		throw new NullPointerException("Invalid Instrument ID");
	}

	public Trade postBuyTrade(Order order) throws Exception {
		// Trade trade = new Trade(order, order.getTargetPrice(), "", Instant.now());

		BigDecimal execPrice;
		
		// get balance from client
		double currentBalance = 1000000;


		if (instrumentPrices == null && order.getDirection() != Direction.BUY) {
			throw new NullPointerException("Bad instrument Prices");
		}

		// Most recent price of instrument
		Price instrPrice = getPriceByInstrumentId(order.getInstrumentId());

		BigDecimal targetPrice = order.getTargetPrice();
		BigDecimal askPrice = instrPrice.getAskPrice();

		BigDecimal lowerBound = askPrice.multiply(BigDecimal.ONE.subtract(tolerance));
		BigDecimal upperBound = askPrice.multiply(BigDecimal.ONE.add(tolerance));

		if (targetPrice.compareTo(lowerBound) < 0 || targetPrice.compareTo(upperBound) > 0) {
			throw new IllegalArgumentException("Buy couldn't get executed, Invalid ask price");
		}

		// will be true in most cases
		execPrice = askPrice; 

		// cashValue = (quantity * execPrice) * (1 + fee)
		BigDecimal cashValue = new BigDecimal(order.getQuantity()).multiply(execPrice).multiply(BigDecimal.ONE.add(fee));

		if (cashValue.compareTo(new BigDecimal(currentBalance)) > 0) {
			throw new Exception("Can't execute trade, not enough balance");
		}

		// Reduce currentBalance and update clientBalance

		Trade trade = new Trade(order, cashValue, "123", Instant.now());
		
		// portfolio.add(new Portfolio(order.getInstrumentId(), instrPrice.instrument.getDescription(), execPrice, trade.getQuantity() , trade.getClientId()));

		return trade;

	}

	public Trade postSellTrade(Order order) throws Exception {

		// accept list of portfolio objects of current client

		BigDecimal execPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
		Trade trade;
		double currentBalance = 1000000;

		List<Trade> portfolio = portfolioService.getPortfolio();


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
						throw new Exception("Sell couldn't get executed, Invalid bid price");
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

				// trade = new Trade(order, cashValue, order.getQuantity(), "S",
				// order.getInstrumentId(), order.getClientId(), "XYZ", execPrice );
				// c.setBalance(currentBalance + cashValue.doubleValue());
				// to update portfolio
				// for (Trade inst : portfolio) {
				// 	if (inst.getInstrumentId().equals(order.getInstrumentId())) {
				// 		if(inst.getQuantity() < order.getQuantity()) {
				// 			throw new Exception("Can not sell more that owned amount");
				// 		}
				// 		else if(inst.getQuantity() == order.getQuantity()) {
				// 			portfolio.remove(inst);
				// 		}
				// 		else {
				// 			inst.setQuantity(inst.getQuantity() - order.getQuantity());
				// 		}
				// 	}
				// }

				return trade;

			} else {
				throw new Exception("Sell couldn't get executed, Invalid bid price");
			}
		} else {
			throw new NullPointerException("Bad portfolio list");
		}

	}
}
