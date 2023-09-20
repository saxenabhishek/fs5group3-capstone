package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import models.Trade;
import services.DbTestUtils;
import services.SimpleDataSource;
import shared.TransactionManager;

public class PortfolioDaoImplTest {
	private JdbcTemplate jdbcTemplate;
	private DbTestUtils dbTestUtils;
	private PortfolioDaoImpl portfolioDaoImpl;
	private SimpleDataSource dataSource;
	private Connection connection;
	private TransactionManager transactionManager;

	@BeforeEach
	void setUp() throws Exception {
		dataSource = new SimpleDataSource();
		portfolioDaoImpl = new PortfolioDaoImpl(dataSource);
		connection = dataSource.getConnection();
		transactionManager = new TransactionManager(dataSource);

		//Start the transaction

		transactionManager.startTransaction(); 

		dbTestUtils = new DbTestUtils(connection);		
		jdbcTemplate = dbTestUtils.initJdbcTemplate();
	}

	@AfterEach
	void tearDown() throws Exception {
		//Roll back the transaction
		transactionManager.rollbackTransaction();

		// Shutdown the DataSource
		dataSource.shutdown();
	}
	
	@Test
	void testPortfolioDaoImplIsNotNull() {
		assertNotNull(portfolioDaoImpl);
	}

	@Test
	void testGetWholePortfolioWorks() {
		List<Trade> holdings = portfolioDaoImpl.getPortfolio("UID001");
		int rows = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "ft_trade", "clientid= 'UID001' and direction= 'B'");
		assertNotNull(holdings);
		assertEquals(holdings.size(), rows);
	}
}
