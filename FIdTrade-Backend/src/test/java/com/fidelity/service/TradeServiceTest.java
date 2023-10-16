package com.fidelity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Direction;
import com.fidelity.business.Instrument;
import com.fidelity.business.Order;
import com.fidelity.shared.Helper;

@SpringBootTest
@Transactional
class TradeServiceTest {
	@Autowired
	TradeService tradeService;

	private final String USERID = "UID003";

	@Test
	void testGetAllInstruments() {
		List<Instrument> portfolio = tradeService.getAllInstruments("");
		assertEquals(9, portfolio.size());
	}

	@Test
	void testGetAllInstrumentById() {
		List<Instrument> portfolio = tradeService.getAllInstruments("T67883");
		assertEquals(1, portfolio.size());
	}

	@Test
	void testGetAllInstrumentByIdThatDoesntExist() {
		List<Instrument> portfolio = tradeService.getAllInstruments("null");
		assertEquals(0, portfolio.size());
	}

	@Test
	void testNullPostBuyOrSellTrade() throws Exception {
		assertThrows(NullPointerException.class, () -> {
			tradeService.postBuyTrade(null);
		});
	}

//	@Test
//	void testBuyTrade() throws Exception {
//		Order order = new Order("T67880", 2, Helper.makeBigDecimal("1.00"), Direction.BUY, USERID, "123",
//				Instant.now());
//		tradeService.processOrder(order);
//
//	}

//	@Test
//	void testSellTrade() throws Exception {
//		Order buyOrder = new Order("T67880", 2, Helper.makeBigDecimal("1.00"), Direction.BUY, USERID, "123",
//				Instant.now());
//		tradeService.processOrder(buyOrder);
//		Order sellOrder = new Order("T67880", 2, Helper.makeBigDecimal("1.02"), Direction.SELL, USERID, "124",
//				Instant.now());
//		tradeService.processOrder(sellOrder);
//	}
}
