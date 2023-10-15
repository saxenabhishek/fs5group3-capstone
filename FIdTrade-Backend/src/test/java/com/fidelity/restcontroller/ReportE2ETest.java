package com.fidelity.restcontroller;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fidelity.business.Order;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:fidtrade_db.sql"},
	 executionPhase = ExecutionPhase.AFTER_TEST_METHOD) 
public class ReportE2ETest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;  // for executing SQL queries
	
//	// Initialize a few Presidents for expected values in test cases
//	private President president1 = new President(1,"George","Washington",1789,1797,"georgewashington.jpg",
//			"On April 30, 1789, George Washington, standing on the balcony of Federal Hall on Wall Street "
//			+ "in New York, took his oath of office as the first President of the United States. "
//			+ "\"As the first of every thing, in our situation will serve to establish a Precedent,\" "
//			+ "he wrote James Madison, \"it is devoutly wished on my part, that these precedents may be "
//			+ "fixed on true principles.\" Born in 1732 into a Virginia planter family, he learned the "
//			+ "morals, manners, and body of knowledge requisite for an 18th century Virginia gentleman.");
//	
//	private President president10 = new President(10,"John","Tyler",1841,1845,"johntyler.jpg",
//			"Dubbed \"His Accidency\" by his detractors, John Tyler was the first Vice President to be "
//			+ "elevated to the office of President by the death of his predecessor. Born in Virginia in "
//			+ "1790, he was raised believing that the Constitution must be strictly construed. He never "
//			+ "wavered from this conviction. He attended the College of William and Mary and studied law.");

	/**
	 * This test verifies the PresidentsRestController can query successfully for all the
	 * Presidents in the database
	 */
	@Test
	public void testgetReportActivity() {
		// get the row count from the Presidents table
		int reportCount = countRowsInTable(jdbcTemplate, "ft_order");
		
		String request = "client/activityReport";

		ResponseEntity<Order[]> response = 
				restTemplate.getForEntity(request, Order[].class);
		
		// verify the response HTTP status is OK
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		// verify that the service returned all Presidents in the database
		Order[] responseReports = response.getBody();
		assertEquals(reportCount, responseReports.length); 
		
		
	}

	/**
	 * This test verifies the PresidentsRestController successfully handles the case
	 * where there are no Presidents in the Presidents.
	 */
	@Test
	public void testQueryForAllPresidents_NoPresidentsInDb() {
		// delete all rows from the Presidents table
		deleteFromTables(jdbcTemplate, "ft_order");
		
		String request = "client/activityReport";

		ResponseEntity<String> response = restTemplate.getForEntity(request, String.class);
		
		// verify the response HTTP status is 204 (NO_CONTENT)
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
		// verify that the response body is empty
		assertTrue(response.getBody() == null || response.getBody().isBlank());
	}

	

}
