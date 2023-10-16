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
	
		List<Order> reportList = dao.getReportActivity("UID001","BUY");
		assertEquals(3, reportList.size());
	}

}
