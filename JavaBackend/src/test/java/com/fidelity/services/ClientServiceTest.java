package com.fidelity.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.models.Preference;
import com.fidelity.services.ClientService;



class ClientServiceTest {
    private ClientService cs;
    private Preference client1 = new Preference("College Fund", "BELOW AVERAGE","40,001 - 60,000", "0-5 years",true);
    private Preference client2 = new Preference("Retirement", "ABOVE AVERAGE","100,001 - 150,000", "10-15 years ",false);
    private String purpose="Middle-life";
    private String risk="AVERAGE";
    private String category="80,001 - 100,000";
    private String length="7-10 years";
    
    @BeforeEach
	public void setUp() {
	}
    
    @Test
    void testConstructor() {
    	cs=new ClientService();
    	assertNotNull(cs);
    }
    
    @Test
    void testEmptyPreference() {
    	cs=new ClientService();
    	assertEquals(0,cs.getPreferences().size());
    	
    }
    
    @Test
    void addPreferenceFailure() {
    	cs=new ClientService();
    	assertThrows(NullPointerException.class, () ->
    	 cs.addPreference(null)
		
	);
    }
    
    @Test
	void addPreferenceSuccess1() {
		cs=new ClientService();
		cs.addPreference(client1);
		cs.addPreference(client2);
		assertEquals(1,cs.getPreferences().size());
		assertTrue(cs.getPreferences().contains(client1));
		assertFalse(cs.getPreferences().contains(client2));
		
	}
	
    

	@Test
	void addPreferenceSuccess2() {
		cs=new ClientService();
		cs.addPreference(client1);
		assertEquals(1,cs.getPreferences().size());
		assertTrue(cs.getPreferences().contains(client1));
		
	}
	
	@Test
	void doesNotAcceptConditions() {
		cs=new ClientService();
		cs.addPreference(client2);
		assertEquals(0,cs.getPreferences().size());
	}
	
	@Test
	void AcceptConditions() {
		cs=new ClientService();
		cs.addPreference(client1);
		assertEquals(1,cs.getPreferences().size());
	}
	
	
	@Test
	void invalidData() {
		cs=new ClientService();
		
		assertThrows(NullPointerException.class, () ->
		cs.updatePreference("Education", "AVERAGE", null,"7-10 years")
	);
		
		
		
	}
	
	@Test
	void updatePreferenceSuccess() {
		cs=new ClientService();
		cs.addPreference(client1);
		cs.updatePreference(purpose,risk,category,length);
		assertEquals(client1.getInvestmentPurpose(),purpose);
		assertEquals(client1.getIncomeCategory(),category);
		
		
		
	}
	
	@Test
	void updatePreferenceFailure() {
		cs=new ClientService();
		cs.addPreference(client1);
		cs.updatePreference(purpose,risk,"100,001 - 150,000",length);
		assertEquals(client1.getInvestmentPurpose(),purpose);
		assertNotEquals(client1.getIncomeCategory(),category);
		
	}
	
	@Test
	void invalidDataOfLengthOne() {
		cs=new ClientService();
		cs.addPreference(client1);
		cs.updatePreference(purpose,"1",category,length);
		assertNotEquals(client1.getRiskTolerance(),risk);
		
		
	}
	
	

}
