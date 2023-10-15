package com.fidelity.restcontroller;


import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fidelity.business.Order;
import com.fidelity.integration.ReportActivityDao;
import com.fidelity.controller.ClientController;


/**
 * PresidentsControllerWebLayerTest defines the web layer integration tests
 * for the PresidentsController REST endpoint.
 */
@WebMvcTest(ClientController.class)
public class ReportWebLayerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ReportActivityDao mockDao;
	
//	private static final List<Order> presidents = Arrays.asList(
//		new President(1, "George", "Washington", 1789, 1797, "georgewashington.jpg", "Chopped down a cherry tree"), 
//		new President(2, "John", "Adams", 1797, 1801, "johnadams.jpg", "Learned and thoughtful")
//	);
//	
//	@Test
//	public void testQueryForAllPresidents() throws Exception {
//		when(mockDao.queryForAllPresidents())
//			.thenReturn(presidents);
//		
//		mockMvc.perform(get("/presidents"))
//		       //.andDo(print())
//		       .andExpect(status().isOk())
//		       .andExpect(jsonPath("$.length()").value(2))
//		       .andExpect(jsonPath("$[0].lastName").value("Washington"))
//		       .andExpect(jsonPath("$[1].lastName").value("Adams"));
//	}
	
	@Test
	public void testQueryForAllReportsEmptyList() throws Exception {
		when(mockDao.getReportActivity())
			.thenReturn(new ArrayList<>());
		
		mockMvc.perform(get("/client/activityReport"))
			    //.andDo(print())
		       .andExpect(status().isNotFound())
		       .andExpect(content().string(is(emptyOrNullString())));
	}
	
	@Test
	public void testQueryForAllPresidentsNullList() throws Exception {
		when(mockDao.getReportActivity())
			.thenReturn(null);
		
		mockMvc.perform(get("/client/activityReport"))
		       //.andDo(print())
		       .andExpect(status().isNotFound())
		       .andExpect(content().string(is(emptyOrNullString())));
	}
	
//	@Test
//	public void testQueryForAllPresidentsDaoException() throws Exception {
//		when(mockDao.getReportActivity())
//			.thenThrow(new RuntimeException("mock exception"));
//		
//		mockMvc.perform(get("/client/activityReport"))
//		       //.andDo(print())
//		       .andExpect(status().isInternalServerError())
//		       .andExpect(content().string(is(emptyOrNullString())));
//	}
	

	
}
