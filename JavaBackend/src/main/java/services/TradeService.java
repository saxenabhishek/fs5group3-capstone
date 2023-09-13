package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.Instrument;
import models.Portfolio;
import models.Price;

public final class TradeService {
		//Instrument i = new Instrument("JP Morgan Chase", "123", 100, 1, "I256", "STOCK");
		List<Price> instrumentPrices;
		List<Portfolio> portfolio;
		public TradeService() {
			instrumentPrices = new ArrayList<>();
			Instrument i1 = new Instrument("JP Morgan", "A123", 100, "N123", 1, "STOCK");
			Price p1 = new Price(i1,  104.00, "N123", 104.25, LocalDateTime.now());
			instrumentPrices.add(p1);


			Instrument i2 = new Instrument("Apple", "A234", 100, "N234", 1, "STOCK");
			Price p2 = new Price(i1, 110.00, "N234", 110.25, LocalDateTime.now());
			instrumentPrices.add(p2);
		}
	public static void getAllInsturments() {
		return instrumentPrices;
	}
	
	public static void postBuyOrSellTrade() {
		
		
	}
	
	public static void getClientProtfolio() {
		
	}
	
}
