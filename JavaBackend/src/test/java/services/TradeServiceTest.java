package services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Order;
import models.Price;

class TradeServiceTest {
	TradeService tradeService;

	@BeforeEach
	void setUp() throws Exception {
		tradeService = new TradeService();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllInsturments() {
		List<Price> portfolio = tradeService.getAllInsturments();
		assertEquals(2, portfolio.size());
	}

	@Test
	void testNullPostBuyOrSellTrade() throws Exception {
		assertThrows(NullPointerException.class, () -> {
			tradeService.postBuyOrSellTrade(null);
		});
	}
	

	@Test
	void testGetClientProtfolio() {
		
	}

}
