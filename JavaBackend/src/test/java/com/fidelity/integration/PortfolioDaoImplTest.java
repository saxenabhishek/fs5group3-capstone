package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.integration.PortfolioDaoImpl;
import com.fidelity.models.Trade;
import com.fidelity.services.DbTestUtils;
import com.fidelity.services.SimpleDataSource;
import com.fidelity.shared.TransactionManager;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="classpath:beans.xml")
@Transactional
public class PortfolioDaoImplTest {	
	
	@Autowired
	private PortfolioDaoImpl portfolioDaoImpl;

//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//		//Roll back the transaction
//		transactionManager.rollbackTransaction();
//
//		// Shutdown the DataSource
//		dataSource.shutdown();
//	}
	
	@Test
	void testPortfolioDaoImplIsNotNull() {
		assertNotNull(portfolioDaoImpl);
	}

	@Test
	void testGetWholePortfolioWorks() {
		List<Trade> holdings = portfolioDaoImpl.getPortfolio("UID001");
//		int rows = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "ft_trade", "clientid= 'UID001' and direction= 'B'");
		assertNotNull(holdings);
		assertEquals(holdings.size(), 1);
	}
}
