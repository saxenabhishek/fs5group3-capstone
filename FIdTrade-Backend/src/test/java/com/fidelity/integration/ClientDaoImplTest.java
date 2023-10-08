package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

import com.fidelity.business.*;


@SpringBootTest
@Transactional
class ClientDaoImplTest {

	@Autowired
	private ClientDaoImpl dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private List<Preference> allPreferencesOnly = Arrays.asList(
		new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T"),
		new Preference("UID002","Retirement",RiskTolerance.ABOVE_AVERAGE,IncomeCategory.EigthyKToOneL,LengthOfInvestment.FiveToSevenYears,"T")
       
	);
    
	//Preference
	@Test
	void testGetAllPreference() {

		List<Preference> preference = dao.queryForAllPreference();

		assertEquals(allPreferencesOnly, preference);
	}


	@Test
	void testInsertPreference() {
		var rowCount = countRowsInTable(jdbcTemplate, "ft_preference");
		var id = "UID003";
	
		var newPref = new Preference(id,"Studies",RiskTolerance.BELOW_AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");		
		int count = dao.insertPreference(newPref);

	
		assertEquals(1,count);

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
		int count = dao.updatePreference(updatePref);
	
		assertEquals(1,count);
		assertEquals(expectedRowCount, countRowsInTable(jdbcTemplate, "ft_preference"));
		/*
		 * //assertEquals(1, countRowsInTableWhere(jdbcTemplate, "ft_preference", """
		 * client_id='UID001' and investment_purpose = 'Studies' and
		 * risk_tolerance='Below Average' and income_category='60,001 - 80,000' and
		 * length_of_investment='0-5 years' and is_checked='T' """));
		 */
	}
	

}
