package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Person;
import com.fidelity.business.ClientIdentification;
import com.fidelity.business.Client;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="classpath:beans.xml")
@Transactional
class ClientRegistrationDaoMyBatisImplTest {
	@Autowired
	private ClientRegistrationDaoMyBatisImpl dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void testInsertPerson() {
		// ARRANGE
		var rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "ft_person");
	
		//id, country, postalCode, dob, email, password
		Person newPerson = new Person("UID009", "United", "577401", LocalDate.of(1999, 07, 02), "ram@example.com", "strongpassword");		
		// ACT
		var success = dao.insertPerson(newPerson);
		// ASSERT
		assertTrue(success);
		
		assertEquals(rowCount + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "ft_person"));
		
		assertEquals(1, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "ft_person", """
								 id = 'UID009'
							 and country = 'United'
							 and postalCode = '577401'
							 and dob=TO_DATE('1999-07-02', 'YYYY-MM-DD')
							 and email ='ram@example.com'
							 and password ='strongpassword'
						 """));
		

	}
	@Test
	void testInsertClientIdentification() {
		// ARRANGE
		var rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "ft_client_identification");
		//long id,String person_id,String type, String value
		ClientIdentification newclientid = new ClientIdentification(456780, "UID001", "Passport", "AB123356");		
		// ACT
		var success = dao.insertClientIdentification(newclientid);
		// ASSERT
		assertTrue(success);
		
		assertEquals(rowCount + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "ft_client_identification"));
		
		assertEquals(1, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "ft_client_identification", """
								 id = 456780
							 and person_id= 'UID001'
							 and identification_type = 'Passport'
							 and identification_value='AB123356'
						 """));
		

	}
	@Test
	void testInsertClient() {
		// ARRANGE
		var rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "ft_client");
		Person person = new Person("UID004", "United", "577401", LocalDate.of(1999, 7, 2), "ram@example.com", "strongpassword");
        dao.insertPerson(person);
		ClientIdentification identification = new ClientIdentification(456781, "UID004", "Passport", "AB823456");
		dao.insertClientIdentification(identification);
        Client newclient = new Client(person, identification);
		// ACT
		var success = dao.insertClient(newclient);
		// ASSERT
		assertTrue(success);
		
		assertEquals(rowCount + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "ft_client"));
		
		assertEquals(1, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "ft_client", """
								 
                id='UID004' and identification_id=456781
							 
						 """));
		

	}
}
