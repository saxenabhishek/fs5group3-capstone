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
import java.text.ParseException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
class TradeHistoryDaoMyBatisImplTest {
//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	java.util.Date parsedDate = dateFormat.parse("2023-09-20 10:30:00");
//    java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	@Autowired
	private TradeHistoryDaoMyBatisImpl dao;

	private List<Order> tradehistoryonly = Arrays.asList(
            new Order("T67878", 8000, new BigDecimal("150.50"), "B", "UID001", "OID004", Instant.parse("2018-09-01T00:00:00.00Z")),
	        new Order("T67880", 30, new BigDecimal("40.25"), "B", "UID003", "OID006", Instant.parse("2022-04-20T10:15:00.00Z")),
	        new Order("T67881", 40, new BigDecimal("60.00"), "B", "UID004", "OID007", Instant.parse("2022-05-10T14:45:00.00Z"))
		    );
	@Test
	void testGetAllDepartments() {
		// ACT
		List<Order> orders = dao.getAllTradeHistory();

		// ASSERT
		// getAllDepartments() doesn't select Employees, so all 
		// Departments' employees lists are null
		assertEquals(tradehistoryonly, orders);
	}

	
}
