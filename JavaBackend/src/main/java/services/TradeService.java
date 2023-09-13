package services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import models.Instrument;
import models.Order;
import models.Portfolio;
import models.Price;
import models.Trade;

public final class TradeService {
		//Instrument i = new Instrument("JP Morgan Chase", "123", 100, 1, "I256", "STOCK");
		List<Price> instrumentPrices;
		List<Portfolio> portfolio;
		double tolerance = 0.05;
		double fee = 0.01;
		public TradeService() {
			instrumentPrices = new ArrayList<>();
			Instrument i1 = new Instrument("JP Morgan", "A123", 100, "N123", 1, "STOCK");
			Price p1 = new Price(i1,  104.00, "N123", 104.25, LocalDateTime.now());
			instrumentPrices.add(p1);


			Instrument i2 = new Instrument("Apple", "A234", 100, "N234", 1, "STOCK");
			Price p2 = new Price(i1, 110.00, "N234", 110.25, LocalDateTime.now());
			instrumentPrices.add(p2);
		}
	public List<Price> getAllInsturments() {
		return instrumentPrices;
	}
	
	public  Trade postBuyOrSellTrade(Order order) throws Exception {
		// Trade trade = new Trade(order, order.getTargetPrice(), "", Instant.now());

		BigDecimal execPrice = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
		Trade trade;
		double currentBalance = 1000.00;

		if(instrumentPrices != null) {
			for(Price p: instrumentPrices) {
				if(p.getInstrumentId().equals(order.getInstrumentId())) {

					if (order.getTargetPrice().doubleValue() > p.getAskPrice().doubleValue() * (1.0 - this.tolerance) &&
				            order.getTargetPrice().doubleValue() < p.getAskPrice().doubleValue() * (1.0 + this.tolerance)) {

				            execPrice = new BigDecimal(p.getAskPrice()).setScale(2, RoundingMode.HALF_DOWN) ;
				     }


				}
			}

			if(execPrice.compareTo(BigDecimal.ZERO) != 0) {
				double cashValuePre = order.getQuantity()  * (1.0 + fee);
				BigDecimal cashValue = new BigDecimal(cashValuePre).multiply(execPrice).setScale(2, RoundingMode.HALF_DOWN);

				if(cashValue.compareTo(new BigDecimal(currentBalance)) > 0) {
					throw new Exception("Can't execute trade, not enough balance");
				}

				trade = new Trade(
					order,
					cashValue,
					"123",
					Instant.now()
				);

				// c.setBalance(currentBalance - cashValue.doubleValue());
				return trade;

			}
			else {
				throw new IllegalArgumentException("Buy couldn't get executed, Invalid ask price");
			}
		}
		else {
			throw new NullPointerException("Bad instrument Prices");
		}



	}
	
	public static void getClientProtfolio() {

	}
	
}
