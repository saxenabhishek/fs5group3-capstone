package com.fidelity.controller;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fidelity.business.*;
import com.fidelity.integration.*;
import com.fidelity.service.ClientService;


@WebMvcTest
public class ClientControllerWebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientService mockBusinessService;
	private List<Preference> preferences;
	
	@BeforeEach
	public void init() {
		preferences = Arrays.asList(
		    new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T"),
		    new Preference("UID002","Retirement",RiskTolerance.ABOVE_AVERAGE,IncomeCategory.EigthyKToOneL,LengthOfInvestment.FiveToSevenYears,"T")
		);
	}

    //Preference
	@Test
	public void testQueryForPreferenceById() throws Exception {
	    String id = "UID001";
		Preference firstPreference = new Preference("UID001","College",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		
		when(mockBusinessService.findPreferenceById(id))
			.thenReturn(firstPreference);
		

		mockMvc.perform(get("/client/preference"+id))
			   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.id").value(id))
			   .andExpect(jsonPath("$.investmentPurpose").value("College"));
			  
	}

	
	
	

	
	@Test
	public void testUpdatePreference() throws Exception {
		Preference preference =  new Preference("UID001","Studies",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		
		when(mockBusinessService.modifyPreference(preference ))
			.thenReturn(1);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		String jsonString = mapper.writeValueAsString(preference);
		
		mockMvc.perform(put("/client/preference/update")
							.contentType(MediaType.APPLICATION_JSON)
							.content(jsonString))
			   //.andDo(print())
			   .andExpect(status().isAccepted())
			   .andExpect(jsonPath("$.rowCount").value(1));
	}



	
	@Test
	public void testAddPreference() throws Exception {
		Preference preference =  new Preference("UID003","Business",RiskTolerance.AVERAGE,IncomeCategory.SixtyKToEigthyK,LengthOfInvestment.ZeroToFiveYears,"T");
		
		when(mockBusinessService.addPreference(preference))
			.thenReturn(1);
	
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonString = mapper.writeValueAsString(preference);
		
		

		mockMvc.perform(post("/client/preference/add")
						   .contentType(MediaType.APPLICATION_JSON)
						   .content(jsonString))
			   //.andDo(print())
			   .andExpect(status().isCreated())
			   .andExpect(jsonPath("$.rowCount").value(1));
	}

	

}
