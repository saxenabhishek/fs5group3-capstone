package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Order;
import com.fidelity.business.Trade;

@SpringBootTest
@Transactional
public class PortfolioDaoImplTest {	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PortfolioDao portfolioDaoImpl;
	
	@Test
	void testPortfolioDaoImplIsNotNull() {
		assertNotNull(portfolioDaoImpl);
	}

	private int countUniqueInstrumentIds(JdbcTemplate jdbcTemplate, String tableName, String condition) {
	    String sql = "SELECT COUNT(DISTINCT instrumentid) FROM " + tableName + " WHERE " + condition;
	    return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Test
	void testGetWholePortfolioTrades() {
		String clientId= "UID001";
		List<Trade> holdings = portfolioDaoImpl.getPortfolioTrades(clientId);
		assertNotNull(holdings);
		int rows= JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "ft_trade", "clientid= '" + clientId + "'");
		assertEquals(holdings.size(), rows);
	}
	
	@Test
	void testGetAggregatedHoldings() {
		String clientId= "UID001";
		String instId= "T67880";
		List<Trade> holdings = portfolioDaoImpl.getHoldings(clientId);
		assertNotNull(holdings);
		int rows= countUniqueInstrumentIds(jdbcTemplate, "ft_trade", "clientid= '" + clientId + "'");
		assertEquals(holdings.size(), rows);
	}
	
	@Test
	void testClientNotFound() {
		assertThrows(NullPointerException.class, () -> portfolioDaoImpl.getPortfolioTrades("UID004"));
	}
	
	@Test
	void testClientPortfolioNotFound() {
		String clientId= "UID002";
		int rows= JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "ft_trade", "clientid= '" + clientId + "'");
		assertEquals(rows, portfolioDaoImpl.getPortfolioTrades(clientId).size());
	}
	
	@Test
	void testGetWholePortfolioForNullClientId() {
		assertThrows(NullPointerException.class, () -> portfolioDaoImpl.getPortfolioTrades(null));
	}
	
	@Test
	void testSizeGetTradeHistory() {
		List<Order> orders = portfolioDaoImpl.getAllTradeHistory("UID001");
		assertNotNull(orders);
		assertEquals(orders.size(), 4);
		
	}
}
