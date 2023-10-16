package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Client;
import com.fidelity.business.Person;
import com.fidelity.business.ClientIdentification;
import com.fidelity.integration.mapper.ClientMapper;

import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;



@SpringBootTest
@Transactional
public class ClientLoginDaoImplTest {	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ClientDao ClientDaoImpl;

	@InjectMocks
    private ClientDaoImpl clientDao;

    @Mock
    private ClientMapper clientMapper;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
	void testClientLoginDaoImplIsNotNull() {
		assertNotNull(ClientDaoImpl);
	}
    
    @Test
    public void testGetClientsByID() {
        // Arrange
        String clientId = "123";
        Client expectedClient = new Client();
        Mockito.when(clientMapper.getClientsByID(clientId)).thenReturn(expectedClient);

        // Act
        Client result = clientDao.getClientsByID(clientId);

        // Assert
        assertEquals(expectedClient, result);
    }

    @Test
    public void testGetClientsByEmail() {
        // Arrange
        String email = "test@example.com";
        Client expectedClient = new Client();
        Mockito.when(clientMapper.getClientsByEmail(email)).thenReturn(expectedClient);

        // Act
        Client result = clientDao.getClientsByEmail(email);

        // Assert
        assertEquals(expectedClient, result);
    }

    @Test
    public void testGetIdFromEmail() {
        // Arrange
        String email = "test@example.com";
        String expectedClientId = "123";
        Mockito.when(clientMapper.getIdFromEmail(email)).thenReturn(expectedClientId);

        // Act
        String result = clientDao.getIdFromEmail(email);

        // Assert
        assertEquals(expectedClientId, result);
    }

    @Test
    public void testCheckIfRowExists() {
        // Arrange
        String data = "test";
        Integer expectedResult = 1;
        Mockito.when(clientMapper.checkIfRowExists(data)).thenReturn(expectedResult);

        // Act
        Integer result = clientDao.checkIfRowExists(data);

        // Assert
        assertEquals(expectedResult, result);
    }


    @Test
    public void testInsertClientIdentification() {
        // Arrange
        ClientIdentification clientIdentification = new ClientIdentification();
        String clientId = "123";

        // Act
        assertDoesNotThrow(() -> clientDao.insertClientIdentification(clientIdentification, clientId));
    }

    @Test
    public void testInsertBalance() {
        // Arrange
        String clientId = "123";
        BigDecimal balance = new BigDecimal("1000");

        // Act
        assertDoesNotThrow(() -> clientDao.insertBalance(clientId, balance));
    }

    @Test
    public void testDoesEmailAlreadyExist() {
        // Arrange
        String email = "test@example.com";
        int expectedResult = 1;
        Mockito.when(clientMapper.doesEmailAlreadyExist(email)).thenReturn(expectedResult);

        // Act
        int result = clientDao.doesEmailAlreadyExist(email);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDoesClientIdentificationAlreadyExist() {
        // Arrange
        Set<ClientIdentification> clientIdentifications = new HashSet<>();
        ClientIdentification clientIdentification = new ClientIdentification();
        clientIdentifications.add(clientIdentification);

        int expectedResult = 1;
        Mockito.when(clientMapper.doesClientIdentificationAlreadyExist(clientIdentification)).thenReturn(expectedResult);

        // Act
        int result = clientDao.doesClientIdentificationAlreadyExist(clientIdentifications);

        // Assert
        assertEquals(expectedResult, result);
    }
    
//  @Test
//  public void testInsertPerson() {
//      // Arrange
//      Person person = new Person(null, null, null, null, null, null);
//
//      // Act
//      assertDoesNotThrow(() -> clientDao.insertPerson(person));
//  }

}