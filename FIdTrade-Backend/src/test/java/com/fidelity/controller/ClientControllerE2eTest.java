package com.fidelity.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;
import static org.springframework.test.jdbc.JdbcTestUtils.dropTables;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.fidelity.business.*;
import com.fidelity.integration.*;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql({"classpath:schema-dev.sql", "classpath:data-dev.sql"}) // change 
public class ClientControllerE2eTest {

	
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;  
	

    //Preference
	@Test
	public void testQueryForAllPreference() {
		
		Preference p1= new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		Preference p2= new Preference("UID002","Retirement",RiskTolerance.ABOVE_AVERAGE,IncomeCategory.EigthyKToOneL,LengthOfInvestment.FiveToSevenYears,"T");
		

		
		int preferenceCount = countRowsInTable(jdbcTemplate, "ft_preference");
		
		String requestUrl = "/investment-preferences/preferences";
		
		ResponseEntity<Preference[]> response = 
			restTemplate.getForEntity(requestUrl, Preference[].class);
		
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	
		Preference[] responsePreference = response.getBody();
		assertEquals(preferenceCount, responsePreference.length); 
	
		assertEquals(p1, responsePreference[0]);
		assertEquals(p2, responsePreference[1]);
	}

	
	@Test
	public void testQueryForAllPreference_NoPreferenceInDb() {
		
		deleteFromTables(jdbcTemplate, "ft_preference");
		
		String requestUrl = "/investment-preferences/preferences";

		ResponseEntity<Preference[]> response = 
			restTemplate.getForEntity(requestUrl, Preference[].class);
		
	
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	
	@Test
	public void testGetPreferenceById_NoPrefgetTable() {
		
		dropTables(jdbcTemplate, "ft_preference");

		String request = "/investment-preferences/preference"+"UID001";

		ResponseEntity<Preference> response = restTemplate.getForEntity(request, Preference.class);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}


	@Test
	public void testQueryForPreferenceById() {
		Preference p1= new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		String requestUrl = "/investment-preferences/preference"+"UID001";

		ResponseEntity<Preference> response = 
			restTemplate.getForEntity(requestUrl, Preference.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		
		Preference responseBody = response.getBody();
		assertEquals(p1, responseBody);
	}


	@Test
	public void testQueryForPreferenceById_NotPresent() {
		String requestUrl = "/investment-preferences/preference"+"UID008";

		ResponseEntity<Preference> response = 
			restTemplate.getForEntity(requestUrl, Preference.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	
	@Test
	public void testAddPreference() throws Exception {
	
		int oldRowCount = countRowsInTable(jdbcTemplate, "ft_preference");
		String id = "UID003";
		Preference p3= new Preference(id,"Abroad",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");

		
		String requestUrl = "/investment-preferences/preference/add";
		
		ResponseEntity<DatabaseRequestResult> response = 
			restTemplate.postForEntity(requestUrl, p3, 
									   DatabaseRequestResult.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		DatabaseRequestResult responseBody = response.getBody();
		assertEquals(1, responseBody.getRowCount());

		
		int newRowCount = countRowsInTable(jdbcTemplate, "ft_preference");
		assertEquals(oldRowCount + 1, newRowCount);


	}


	@Test
	public void testAddPreference_DuplicateId() throws Exception {
	
		int oldRowCount = countRowsInTable(jdbcTemplate, "ft_preference");
		String id = "UID002";
		Preference p2= new Preference(id,"Retirement",RiskTolerance.ABOVE_AVERAGE,IncomeCategory.EigthyKToOneL,LengthOfInvestment.FiveToSevenYears,"T");

		String requestUrl = "/investment-preferences/preference/add";
		ResponseEntity<DatabaseRequestResult> response = 
			restTemplate.postForEntity(requestUrl,p2, 
									   DatabaseRequestResult.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	
		int newRowCount = countRowsInTable(jdbcTemplate, "ft_preference");
		assertEquals(oldRowCount, newRowCount);
	}


	@Test
	public void testUpdatePreference() throws Exception {
		
		int oldCount = countRowsInTable(jdbcTemplate, "ft_preference");
	    String id = "UID002";
		Preference p2= new Preference(id,"Wedding",RiskTolerance.ABOVE_AVERAGE,IncomeCategory.EigthyKToOneL,LengthOfInvestment.FiveToSevenYears,"T");

		
		String requestUrl = "/investment-preferences/preference/update";
		RequestEntity<Preference> requestEntity = 
			RequestEntity.put(new URI(requestUrl)) 
						 .contentType(MediaType.APPLICATION_JSON) 
						 .accept(MediaType.APPLICATION_JSON)
						 .body(p2);

		ResponseEntity<DatabaseRequestResult> response = 
			restTemplate.exchange(requestEntity, DatabaseRequestResult.class);


		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		DatabaseRequestResult responseDto = response.getBody(); 
		assertEquals(1, responseDto.getRowCount());

		
		int newCount = countRowsInTable(jdbcTemplate, "ft_preference");
		assertEquals(oldCount, newCount);

	
	}

	


}

