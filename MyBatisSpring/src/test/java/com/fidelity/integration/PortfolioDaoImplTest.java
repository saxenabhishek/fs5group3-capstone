package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Trade;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="classpath:beans.xml")
@Transactional
public class PortfolioDaoImplTest {	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PortfolioDaoImpl portfolioDaoImpl;
	
	@Test
	void testPortfolioDaoImplIsNotNull() {
		assertNotNull(portfolioDaoImpl);
	}

	@Test
	void testGetWholePortfolio() {
		String clientId= "UID001";
		List<Trade> holdings = portfolioDaoImpl.getPortfolio(clientId);
		assertNotNull(holdings);
		int rows= JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "ft_trade", "clientid= '" + clientId + "'");
		assertEquals(holdings.size(), rows);
	}
	
	@Test
	void testClientNotFound() {
		assertThrows(NullPointerException.class, () -> portfolioDaoImpl.getPortfolio("UID004"));
	}
	
	@Test
	void testClientPortfolioNotFound() {
		String clientId= "UID002";
		int rows= JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "ft_trade", "clientid= '" + clientId + "'");
		assertEquals(rows, portfolioDaoImpl.getPortfolio(clientId).size());
	}
	
	@Test
	void testGetWholePortfolioForNullClientId() {
		assertThrows(NullPointerException.class, () -> portfolioDaoImpl.getPortfolio(null));
	}
}
