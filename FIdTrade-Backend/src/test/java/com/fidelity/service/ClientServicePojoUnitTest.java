package com.fidelity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import com.fidelity.business.*;
import com.fidelity.integration.*;



class ClientServicePojoUnitTest {
	
	@Mock
	private ClientDao mockDao;

	@InjectMocks
	private ClientServiceImpl service;
	

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);		
	}

	//Preference
	@Test
	void testFindAllPreference() {
		
		preferences = Arrays.asList(
		    new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T"),
		    new Preference("UID002","Retirement",RiskTolerance.ABOVE_AVERAGE,IncomeCategory.EigthyKToOneL,LengthOfInvestment.FiveToSevenYears,"T")
		);

		
		when(mockDao.queryForAllPreference)
        	.thenReturn(preferences);
         
		
		List<Preference> response = service.findAllPreference();

		assertEquals(preferences, response);
		
	}

	@Test
	void testFindAllPreference_DaoReturnsEmptyList() {
		
		when(mockDao.queryForAllPreference())
        	.thenReturn(new ArrayList<>());
		
		List<Preference> response = service.findAllPreference();

		assertEquals(0, response.size());
		
	}


	@Test
	void testFindPreferenceById() {
		Preference p1= new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		
		when(mockDao.queryForPreferenceById("UID001"))
			.thenReturn(p1);
        
		Preference response = service.findPreferenceById("UID001");
		
		assertEquals(p1, response);
	}

	@Test
	void testAddPreference() {
		Preference p1= new Preference("UID003","Added",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		
		when(mockDao.insertPreference(p1))
			.thenReturn(1);

		int rowsInserted = service.addPreference(preference);
		
		assertEquals(1, rowsInserted);		
	}

	

	@Test
	void testAddPreference_DaoThrowsDuplicateKeyException() {
		Preference preference= new Preference("UID002","Retirement",RiskTolerance.ABOVE_AVERAGE,IncomeCategory.EigthyKToOneL,LengthOfInvestment.FiveToSevenYears,"T");
		
		when(mockDao.insertPreference(preference))
        	.thenThrow(new DuplicateKeyException("mock DAO exception"));
	
       assertThrows(ServerWebInputException.class, () ->
			service.addPreference(preference)
		);
	}
	
	@Test
	void testAddPreference_NullPreference() {
       assertThrows(ServerWebInputException.class, () ->
			service.addPreference(null)
		);
	}
	

	
	@Test
	void testUpdatePreference() {
		Preference preference= new Preference("UID002","Updated",RiskTolerance.ABOVE_AVERAGE,IncomeCategory.EigthyKToOneL,LengthOfInvestment.FiveToSevenYears,"T");
		
		
		when(mockDao.updatePreference(preference))
			.thenReturn(1);
		
		int count = service.modifyPreference(preference);
		
		assertEquals(1, count);						
	}
	

}
