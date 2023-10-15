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
	
//	private static final List<Order> expectedReports = Arrays.asList(
//			new Order(
//	                "OID001",
//	                50,
//	                new BigDecimal("1161.42"),
//	                Direction.BUY,
//	                "UID001",
//	                "Q123",
//	                Instant.parse("2023-09-20T10:30:00.000000Z")
//	        	);
//	
//	@Test
//	public void testGetAllPresidentsSuccess() throws Exception {
//		when(mockDao.queryForAllPresidents())
//			.thenReturn(expectedPresidents);
//		
//		ResponseEntity<List<President>> responseStatus = controller.queryForAllPresidents();
//		
//		HttpStatus statusCode = responseStatus.getStatusCode();
//		assertEquals(HttpStatus.OK, statusCode);
//		List<President> actualPresidents = responseStatus.getBody();
//		assertEquals(expectedPresidents, actualPresidents);
//	}

	@Test
	public void testGetAllPresidentsDaoReturnsEmptyList() throws Exception {
		when(mockDao.getReportActivity())
			.thenReturn(new ArrayList<Order>());
		
		ResponseEntity<List<Order>> responseStatus = controller.getActivityReport();
		
		HttpStatus statusCode = responseStatus.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statusCode);
		List<Order> actualPresidents = responseStatus.getBody();
		assertNull(actualPresidents);
	}

	@Test
	public void testGetAllPresidentsDaoReturnsNull() throws Exception {
		when(mockDao.getReportActivity())
			.thenReturn(null);
		
		ResponseEntity<List<Order>> responseStatus = controller.getActivityReport();
		
		HttpStatus statusCode = responseStatus.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, statusCode);
		List<Order> actualPresidents = responseStatus.getBody();
		assertNull(actualPresidents);
	}

//	@Test
//	public void testGetAllPresidentsDaoThrowsException() throws Exception {
//		when(mockDao.getReportActivity())
//			.thenThrow(new RuntimeException("mock exception"));
//		
//		ServerErrorException ex = assertThrows(ServerErrorException.class, 
//			() -> controller.getActivityReport()
//		);
//			
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatus());
//	}
	@Test
	public void testGetAllPresidentsDaoThrowsException() throws Exception {
	    when(mockDao.getReportActivity())
	        .thenThrow(new RuntimeException("mock exception"));

	    RuntimeException ex = assertThrows(RuntimeException.class, 
	        () -> controller.getActivityReport()
	    );

	    assertEquals("mock exception", ex.getMessage());
	}

}

	

