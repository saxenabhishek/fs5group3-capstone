package com.fidelity.restcontroller;

 

 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

 

import java.util.ArrayList;
import java.util.List;

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerErrorException;

 

import com.fidelity.business.Order;
import com.fidelity.controller.ClientController;
import com.fidelity.integration.ReportActivityDao;

 

 

public class ReportPojoUnitTest {
	@Mock
	private ReportActivityDao mockDao;
	@Mock
	private Logger mockLogger;

	@InjectMocks
	private ClientController controller;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

 

 

	@Test
	public void testGetAllPresidentsDaoReturnsEmptyList() throws Exception {
		when(mockDao.getReportActivity("UID001", "BUY"))
			.thenReturn(new ArrayList<Order>());

		ResponseEntity<List<Order>> responseStatus = controller.getActivityReport("UID001", "BUY");

		HttpStatus statusCode = responseStatus.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statusCode);
		List<Order> actualPresidents = responseStatus.getBody();
		assertNull(actualPresidents);
	}

 

	@Test
	public void testGetAllPresidentsDaoReturnsNull() throws Exception {
		when(mockDao.getReportActivity("UID001", "BUY"))
			.thenReturn(null);

		ResponseEntity<List<Order>> responseStatus = controller.getActivityReport("UID001", "BUY");

		HttpStatus statusCode = responseStatus.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statusCode);
		List<Order> actualPresidents = responseStatus.getBody();
		assertNull(actualPresidents);
	}

 

 

	@Test
	public void testGetAllPresidentsDaoThrowsException() throws Exception {
	    when(mockDao.getReportActivity("UID001", "BUY"))
	        .thenThrow(new RuntimeException("mock exception"));

 

	    RuntimeException ex = assertThrows(RuntimeException.class, 
	        () -> controller.getActivityReport("UID001", "BUY")
	    );

 

	    assertEquals("mock exception", ex.getMessage());
	}

 

}