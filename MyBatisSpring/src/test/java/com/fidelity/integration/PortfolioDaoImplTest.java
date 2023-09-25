package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fidelity.business.Trade;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="classpath:beans.xml")
//@Transactional
public class PortfolioDaoImplTest {	
	
	@Autowired
	private PortfolioDaoImpl portfolioDaoImpl;
	
	@Test
	void testPortfolioDaoImplIsNotNull() {
		assertNotNull(portfolioDaoImpl);
	}

	@Test
	void testGetWholePortfolioWorks() {
		List<Trade> holdings = portfolioDaoImpl.getPortfolio("UID001");
		assertNotNull(holdings);
		assertEquals(holdings.size(), 1);
	}
}
