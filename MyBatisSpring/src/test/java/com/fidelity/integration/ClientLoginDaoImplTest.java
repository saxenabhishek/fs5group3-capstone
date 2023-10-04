package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class ClientLoginDaoImplTest {	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ClientLoginDao ClientLoginDaoImpl;

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