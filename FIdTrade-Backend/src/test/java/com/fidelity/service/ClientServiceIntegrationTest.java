package com.fidelity.service;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;
import org.springframework.transaction.annotation.Transactional;
import com.fidelity.business.*;


@SpringBootTest
@Transactional
class ClientServiceIntegrationTest {
	
	@Autowired
	private ClientService service;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	
	
	private static List<Preference> allPreferences = Arrays.asList(
		    new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T"),
		    new Preference("UID002","Retirement",RiskTolerance.ABOVE_AVERAGE,IncomeCategory.EigthyKToOneL,LengthOfInvestment.FiveToSevenYears,"T")
		);

	//Preference
	@Test
	void testGetAllPreferences() {
		List<Preference> preferences = service.findAllPreference();
		
		assertEquals(allPreferences, preferences);
	}

	@Test
	void testFindPreferenceById() {
		String id = "UID001";
		Preference firstPreference = new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		
		Preference preference = service.findPreferenceById(id);
		
		
		assertEquals(firstPreference, preference);
	}

	
	
	@Test
	void testInsertPreference() {
		String id = "UID003";
		
		
		assertEquals(countRowsInTableWhere(jdbcTemplate, "ft_preference", "id = " + id), 0);

		Preference preference = new Preference(id,"Added",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		

		int rows = service.addPreference(preference);
			
		assertEquals(1, rows);
		
		assertEquals(countRowsInTableWhere(jdbcTemplate, "ft_preference", "id = " + id), 1);
	}
	
	@Test
	void testUpdatePreference() {
		String id = "UID001";
		

		Preference preference = new Preference(id,"Updated",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		
	
		int rows = service.modifyPreference(preference);
		
		assertEquals(1, rows);
	

	}

}
