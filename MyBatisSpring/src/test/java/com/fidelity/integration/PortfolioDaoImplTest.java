package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Trade;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="classpath:beans.xml")
@Transactional
public class PortfolioDaoImplTest {	
	
	@Autowired
	private PortfolioDaoImpl portfolioDaoImpl;
	
	@Test
	void testPortfolioDaoImplIsNotNull() {
		assertNotNull(portfolioDaoImpl);
	}

	@Test
	void testGetWholePortfolio() {
		List<Trade> holdings = portfolioDaoImpl.getPortfolio("UID001");
		assertNotNull(holdings);
		assertEquals(holdings.size(), 3);
	}
	
	@Test
	void testGetWholePortfolioForNullClientId() {
		assertThrows(NullPointerException.class, () -> portfolioDaoImpl.getPortfolio(null));
	}
}
