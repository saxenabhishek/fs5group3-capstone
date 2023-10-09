package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Order;

@SpringBootTest
@Transactional
class TradeHistoryDaoMyBatisImplTest {

	@Autowired
	private TradeHistoryDaoMyBatisImpl dao;

	@Test
	void testSizeGetTradeHistory() {
		List<Order> orders = dao.getAllTradeHistory();
		assertNotNull(orders);
		assertEquals(orders.size(), 4);

	}

}
