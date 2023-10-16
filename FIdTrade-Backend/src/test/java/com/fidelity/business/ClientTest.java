package com.fidelity.business;

 

 

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

 

 

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

 

 

import org.junit.jupiter.api.Test;

 

 

public class ClientTest {
	Set<ClientIdentification> identification ;{
	identification= new HashSet<>();	
	identification.add(new ClientIdentification("Passport", "AB123456"));}

 

 

	@Test
    void testValidClientCreation() {
        Person person = new Person("UID001", "India", "12345", "2000, 01, 01", "testing@yahoo.com", "password");

 

        BigDecimal walletBalance = null;
        assertDoesNotThrow(() ->
                new Client(person, identification,walletBalance));
    }

 

 

    
	@Test
    void testNullPerson() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(null,  identification  ,BigDecimal.ZERO));
    }

 

 

    @Test
    void testNullClientIdentification() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person("UID010", "US", "123", "2000, 01, 01", "testemail@gmail.com","password"), null, BigDecimal.ZERO));
    }

 

 

    

 

    @Test
    void testNullClient() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(null,null,null));
    }

 

 

    

 

 

    void testEmptyPerson() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person("","","","","",""), identification, BigDecimal.ZERO));
    }

 

 

    @Test
    void testEmptyClient() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person("","","","","",""),identification,null));
    }    
}