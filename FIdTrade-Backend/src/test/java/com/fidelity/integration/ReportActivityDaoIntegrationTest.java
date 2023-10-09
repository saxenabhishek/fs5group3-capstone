package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Direction;
import com.fidelity.business.Order;
@SpringBootTest
@Transactional
class ReportActivityDaoIntegrationTest {
	@Autowired
	private ReportActivityDao dao;
	
	@Test
	void testgetReportActivity() {
	//	Order washington = new Order("OID001", "Q123",50, 1161.42, Direction.BUY, "UID001",20-SEP-23 10.30.00.000000000 AM);


		List<Order> reportList = dao.getReportActivity();
		assertEquals(3, reportList.size());
//		assertEquals(washington, presidentList.get(0));
//		assertEquals(tyler, presidentList.get(9));
	}

}
