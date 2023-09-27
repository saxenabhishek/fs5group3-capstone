package com.fidelity.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.business.Direction;
import com.fidelity.business.Order;
import com.fidelity.business.Price;
import com.fidelity.shared.Helper;

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
			tradeService.postBuyTrade(null);
		});
	}

	@Test
	void testBuyTrade() throws Exception{
		Order order = new Order("N234", 2, Helper.makeBigDecimal("102"), Direction.BUY, "100012242", "123", Instant.now());
		tradeService.postBuyTrade(order);

	}
//	
//	@Test
//	void testSellTrade() throws Exception{
//		Order buyorder = new Order("N234", 2, Helper.makeBigDecimal("102"), Direction.BUY.getStringValue(), "100012242", "123", Instant.now());
//		tradeService.postBuyTrade(buyorder);
//		Order sellorder = new Order("N234", 2, Helper.makeBigDecimal("110"), Direction.SELL.getStringValue(), "100012242", "123", Instant.now());
//		tradeService.postSellTrade(sellorder);
//	}


	

	@Test
	void testGetClientProtfolio() {
		
	}

}
