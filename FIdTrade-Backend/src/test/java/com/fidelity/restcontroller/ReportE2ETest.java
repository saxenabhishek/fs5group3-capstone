//package com.fidelity.restcontroller;
//import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
//import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
//
//import com.fidelity.business.IncomeCategory;
//import com.fidelity.business.LengthOfInvestment;
//import com.fidelity.business.Order;
//import com.fidelity.business.Preference;
//import com.fidelity.business.RiskTolerance;
//
// 
//
//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
//@Sql(scripts = { "classpath:fidtrade_db.sql"},
//	 executionPhase = ExecutionPhase.AFTER_TEST_METHOD) 
//public class ReportE2ETest {
//	@Autowired
//	private TestRestTemplate restTemplate;
//
// 
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;  // for executing SQL queries
//
//
// 
//
//	@Test
//	public void testgetReportActivity() {
//		// get the row count from the Presidents table
//		int reportCount = countRowsInTable(jdbcTemplate, "ft_order");
//
//		String request = "/client/activityReport/";
//
// 
//
//		ResponseEntity<Order[]> response = 
//				restTemplate.getForEntity(request, Order[].class);
//
//		// verify the response HTTP status is OK
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//
//		// verify that the service returned all Presidents in the database
//		Order[] responseReports = response.getBody();
//		assertEquals(reportCount, responseReports.length); 
//
//
//	}
////	@Test
////	public void testQueryForPreferenceById() {
////		Preference p1= new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
////		String requestUrl = "/client/preference/UID001";
////
////		ResponseEntity<Preference> response = 
////			restTemplate.getForEntity(requestUrl, Preference.class);
////		
////		assertEquals(HttpStatus.OK, response.getStatusCode());
////		
////		
////		Preference responseBody = response.getBody();
////		assertEquals(p1, responseBody);
////	}
//
// 
////
////	
////	@Test
////	public void testQueryForAllReports_NoReportsInDb() {
////		// delete all rows from the Presidents table
////		deleteFromTables(jdbcTemplate, "ft_order");
////
////		String request = "client/activityReport";
////
//// 
////
////		ResponseEntity<String> response = restTemplate.getForEntity(request, String.class);
////
////		// verify the response HTTP status is 204 (NO_CONTENT)
////		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
////
////		// verify that the response body is empty
////		assertTrue(response.getBody() == null || response.getBody().isBlank());
////	}
//
// 
//
//	
//
// 
//
//}