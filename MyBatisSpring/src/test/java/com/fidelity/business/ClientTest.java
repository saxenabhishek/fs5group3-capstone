package com.fidelity.business;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ClientTest {

	@Test
    void testValidClientCreation() {
        Person person = new Person("UID001", "India", "12345", LocalDate.of(2000, 01, 01), "testing@yahoo.com", "password");
        ClientIdentification identification = new ClientIdentification(2345678, "UID001", "Passport", "AB123456");
        assertDoesNotThrow(() ->
                new Client(person, identification));
    }

    @Test
    void testNullPerson() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(null, new ClientIdentification(2345678, "UID001", "Passport", "AB123456")));
    }

    @Test
    void testNullClientIdentification() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person("UID010", "US", "123", LocalDate.of(2000, 01, 01), "testemail@gmail.com","password"), null));
    }

    @Test
    void testNullClient() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(null,null));
    }

    @Test
    void testEmptyClientIdentification() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person("UID010", "US", "12345678", LocalDate.of(2000, 01, 01), "testemail@gmail.com", "password"), new ClientIdentification(-1, "", "", "")));
    }

    void testEmptyPerson() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person("","","",null,"",""), new ClientIdentification(3910271, "UID001", "Passport", "ABC12345")));
    }
    @Test
    void testEmptyClient() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person("","","",null,"",""), new ClientIdentification(-1, "", "", "")));
    }    
}
