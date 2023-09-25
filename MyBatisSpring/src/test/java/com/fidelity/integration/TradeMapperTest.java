package com.fidelity.integration;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.integration.TradeMapper;
import com.fidelity.business.Direction;
import com.fidelity.business.Order;
import com.fidelity.business.Trade;
import com.fidelity.shared.Helper;

class TradeMapperTest {

//	SimpleDataSource ds = new SimpleDataSource();
//	TradeMapper tradeDao = new TradeMapper(ds);
//	TransactionManager tm = new TransactionManager(ds);
//
//	@BeforeEach
//	void setUp() throws Exception {
//		tm.startTransaction();
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//		tm.rollbackTransaction();
//	}
//
//	@Test
//	void testAddTrade() {
//		Order order = new Order("T67897", 2, Helper.makeBigDecimal("102"), Direction.BUY.getStringValue(), "UID001","123", Instant.now());
//		tradeDao.addOrder(order);
//		Trade trade = new Trade(order, new BigDecimal("1000.00"), "123", Instant.now());
//
//		tradeDao.addTrade(trade);
//	}

}
