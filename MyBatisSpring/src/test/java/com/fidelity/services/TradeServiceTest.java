package com.fidelity.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Direction;
import com.fidelity.business.Instrument;
import com.fidelity.business.Order;
import com.fidelity.business.Price;
import com.fidelity.shared.Helper;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="classpath:beans.xml")
@Transactional
class TradeServiceTest {
	@Autowired
	TradeService tradeService;


	@Test
	void testGetAllInstruments() {
		List<Instrument> portfolio = tradeService.getAllInstruments("");
		assertEquals(2, portfolio.size());
	}

	@Test
	void testGetAllInstrumentById() {
		List<Instrument> portfolio = tradeService.getAllInstruments("ID1232");
		assertEquals(2, portfolio.size());
	}


		@Test
	void testGetAllInstrumentByIdThatDoesntExist() {
		List<Instrument> portfolio = tradeService.getAllInstruments("ID1232");
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
	
	@Test
	void testSellTrade() throws Exception{
		Order buyorder = new Order("N234", 2, Helper.makeBigDecimal("102"), Direction.BUY, "100012242", "123", Instant.now());
		tradeService.postBuyTrade(buyorder);
		Order sellorder = new Order("N234", 2, Helper.makeBigDecimal("110"), Direction.SELL, "100012242", "123", Instant.now());
		tradeService.postSellTrade(sellorder);
	}
}
