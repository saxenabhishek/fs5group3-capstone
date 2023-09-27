package com.fidelity.integration;


import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fidelity.business.Order;
import com.fidelity.business.Trade;

import java.text.ParseException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
class TradeHistoryDaoMyBatisImplTest {

	@Autowired
	private TradeHistoryDaoMyBatisImpl dao;

	private List<Order> tradehistoryonly = Arrays.asList(
			
            new Order("T67878", 8000, new BigDecimal("0.98546875"), "BUY", "UID001", "OID002",Instant.parse("2022-04-20T10:15:00.00Z")

		    ));
	@Test
	void testSizeGetAllDepartments() {
			List<Order> orders = dao.getAllTradeHistory();
		assertNotNull(orders);
		assertEquals(orders.size(), 3);
		
	}
	

	
}
