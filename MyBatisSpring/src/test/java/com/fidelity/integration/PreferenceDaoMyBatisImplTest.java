package com.fidelity.integration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

import com.fidelity.business.*;
import com.fidelity.business.RiskTolerance;
import com.fidelity.business.IncomeCategory;
import com.fidelity.business.LengthOfInvestment;



@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
class PreferenceDaoMyBatisImplTest {

	@Autowired
	private PreferenceDaoMyBatisImpl dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private List<Preference> allPreferencesOnly = Arrays.asList(
		new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T"),
		new Preference("UID002","Retirement",RiskTolerance.ABOVE_AVERAGE,IncomeCategory.EigthyKToOneL,LengthOfInvestment.FiveToSevenYears,"T")
       
	);

	@Test
	void testGetAllPreference() {

		List<Preference> preference = dao.getAllPreference();

		assertEquals(allPreferencesOnly, preference);
	}


	@Test
	void testInsertPreference() {
		var rowCount = countRowsInTable(jdbcTemplate, "ft_preference");
		var id = "UID003";
	
		var newPref = new Preference(id,"Studies",RiskTolerance.BELOW_AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");		
		var success = dao.insertPreference(newPref);

	
		assertTrue(success);

		assertEquals(rowCount + 1, countRowsInTable(jdbcTemplate, "ft_preference"));
		/*
		 * var whereCondition = """ client_id='UID003' and investment_purpose =
		 * 'Studies' and risk_tolerance='Below Average' and income_category='60,001 -
		 * 80,000' and length_of_investment='0-5 years' and is_checked='T'
		 * 
		 * """;
		 */
		/*
		 * //assertEquals(1, countRowsInTableWhere(jdbcTemplate, "ft_preference",
		 * String.format(whereCondition, id)));
		 */

	}

	@Test
	void testUpdatePreference() {
		var expectedRowCount = countRowsInTable(jdbcTemplate, "ft_preference");
        var id = "UID001";
		var updatePref = new Preference(id,"Studies",RiskTolerance.BELOW_AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
	
		assertEquals(0, countRowsInTableWhere(jdbcTemplate, "ft_preference",
									"client_id= 1 and investment_purpose = 'Studies'"));
		var success = dao.updatePreference(updatePref);
	
		assertTrue(success);
		assertEquals(expectedRowCount, countRowsInTable(jdbcTemplate, "ft_preference"));
		/*
		 * //assertEquals(1, countRowsInTableWhere(jdbcTemplate, "ft_preference", """
		 * client_id='UID001' and investment_purpose = 'Studies' and
		 * risk_tolerance='Below Average' and income_category='60,001 - 80,000' and
		 * length_of_investment='0-5 years' and is_checked='T' """));
		 */
	}
	@Test
	void testEmptyStringPurpose() {
		var expectedRowCount = countRowsInTable(jdbcTemplate, "ft_preference");
        var id1 = "UID001";
        var id2= "UID003";
		var updatePref = new Preference(id1,"",RiskTolerance.BELOW_AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
	    var insertPref=new Preference(id2,"",RiskTolerance.BELOW_AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		assertEquals(0, countRowsInTableWhere(jdbcTemplate, "ft_preference",
									"client_id= 1 and investment_purpose = 'Studies'"));
	  
		
		
		assertThrows(IllegalArgumentException.class, () ->
		 dao.updatePreference(updatePref)
	    );
		assertThrows(IllegalArgumentException.class, () ->
		 dao.insertPreference(insertPref)
	    );
		
		
		
		assertEquals(expectedRowCount, countRowsInTable(jdbcTemplate, "ft_preference"));
	
		
	}


}
