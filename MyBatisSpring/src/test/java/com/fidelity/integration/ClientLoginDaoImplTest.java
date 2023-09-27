package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;
import com.fidelity.business.Client;
import com.fidelity.business.Person;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="classpath:beans.xml")
@Transactional
public class ClientLoginDaoImplTest {	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ClientLoginDaoImpl ClientLoginDaoImpl;

	@Test
	void testClientLoginDaoImplIsNotNull() {
		assertNotNull(ClientLoginDaoImpl);
	}
	
	@Test
	void testvalidEmail() {
		assertNotNull(NullPointerException.class, () -> ClientLoginDaoImpl.getEmail("john@example.com"));
	}
	
	@Test
	void testvalidPassword() {
		assertNotNull(NullPointerException.class, () -> ClientLoginDaoImpl.getPassword(null,"securepassword"));
	}
	
	@Test
	void testvalidEmailandPasswordPair() {
		assertNotNull(NullPointerException.class, () -> ClientLoginDaoImpl.getEmail("john@example.com"));
		assertNotNull(NullPointerException.class, () -> ClientLoginDaoImpl.getPassword("john@example.com","securepassword"));
	}
	
	@Test
	void testEmailNotFound() {
		assertThrows(NullPointerException.class, () -> ClientLoginDaoImpl.getEmail("sivakailash123@gmail.com"));
	}
	
	@Test
	void testPasswordNotFound() {
		assertThrows(NullPointerException.class, () -> ClientLoginDaoImpl.getPassword("john@example.com", "abc123xyz"));
	}
	
	@Test
	void testNullEmailId() {
		assertThrows(NullPointerException.class, () -> ClientLoginDaoImpl.getEmail(null));
	}
	
	@Test
	void testEmptyEmailId() {
		assertThrows(NullPointerException.class, () -> ClientLoginDaoImpl.getEmail(""));
	}
	
	@Test
	void testNullPassword() {
		assertThrows(NullPointerException.class, () -> ClientLoginDaoImpl.getPassword("john@example.com", null));
	}
	
	@Test
	void testEmptyPassword() {
		assertThrows(NullPointerException.class, () -> ClientLoginDaoImpl.getPassword("john@example.com", ""));
	}
	
	@Test
	void testNullEmailandPassword() {
		assertThrows(NullPointerException.class, () -> ClientLoginDaoImpl.getPassword(null, null));
	}
	
	@Test
	void testEmptyEmailandPassword() {
		assertThrows(NullPointerException.class, () -> ClientLoginDaoImpl.getPassword("", ""));
	}
	
}