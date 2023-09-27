package com.fidelity.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Direction;
import com.fidelity.business.Order;
import com.fidelity.business.Price;
import com.fidelity.business.Trade;
import com.fidelity.shared.Helper;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="classpath:beans.xml")
@Transactional
class TradeMapperTest {

	Logger logger = LoggerFactory.getLogger(TradeMapperTest.class);

    @Autowired
	TradeDao tradeDao;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void testAddTrade() {
		Order order = new Order("T67897", 2, Helper.makeBigDecimal("102"), Direction.BUY, "UID001","123", Instant.now());
		tradeDao.addOrder(order);
		
		Integer lengthOfTbl = JdbcTestUtils.countRowsInTable(jdbcTemplate, "ft_trade");
		Trade trade = new Trade(order, new BigDecimal("1000.00"), "123", Instant.now());
		tradeDao.addTrade(trade);

		assertEquals(lengthOfTbl + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "ft_trade"));
	}


	@Test
	void testAddOrder() {
		Integer lengthOfTbl = JdbcTestUtils.countRowsInTable(jdbcTemplate, "ft_order");
		Order order = new Order("T67897", 2, Helper.makeBigDecimal("102"), Direction.BUY, "UID001","123", Instant.now());
		tradeDao.addOrder(order);
		assertEquals(lengthOfTbl + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "ft_order"));
	}

	@Test
	void testGetAllInstruments(){
	
	}

	@Test
	void testGetAllInstrumentbyId(){
		
	}


	@Test
	void testGetAllPrices(){
		// List<Price> prices = tradeDao.getAllPrices();
		// logger.debug(prices.toString());
	}

	@Test
	void testGetPricesById(){

	}

}
