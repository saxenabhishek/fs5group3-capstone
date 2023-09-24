package dao;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Direction;
import models.Order;
import models.Trade;
import services.SimpleDataSource;
import shared.Helper;
import shared.TransactionManager;

class TradeDaoTest {

	SimpleDataSource ds = new SimpleDataSource();
	TradeDao tradeDao = new TradeDao(ds);
	TransactionManager tm = new TransactionManager(ds);

	@BeforeEach
	void setUp() throws Exception {
		tm.startTransaction();
	}

	@AfterEach
	void tearDown() throws Exception {
		tm.rollbackTransaction();
	}

	@Test
	void testAddTrade() {
		Order order = new Order("T67897", 2, Helper.makeBigDecimal("102"), Direction.BUY.getStringValue(), "UID001","123", Instant.now());
		tradeDao.addOrder(order);
		Trade trade = new Trade(order, new BigDecimal("1000.00"), "123", Instant.now());

		tradeDao.addTrade(trade);
	}

}
