package com.fidelity.business;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PersonTest {
	
	@Test
    void testValidPersonCreation() {
        assertDoesNotThrow(() ->
                new Person("UID001", "India", "12345", "2000, 01, 01", "test@gmail.com", "password"));
    }

    @Test
    void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person("UID001", "US", "12345678", "2000, 01, 01", "invalid-email", "password3"));
    }

    @Test
    void testNullEmail() {
        assertThrows(NullPointerException.class, () ->
                new Person("UID001", "India", "12345", "2000, 01, 01", null, "password4"));
    }

    @Test
    void testEmptyPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person("UID001", "US", "12345678", "2000, 01, 01", "test3@domain3.com", ""));
    }

    @Test
    void testNullPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person("UID001", "Ireland", "123", "2000, 01, 01", "test4@domain4.com", null));
    }

    @Test
    void testNullCountry() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person("UID001", null, "123", "2000, 01, 01", "test4@domain4.com", "password5"));
    }

    @Test
    void testEmptyCountry() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person("UID001", "", "123", "2000, 01, 01", "test4@domain4.com", "password6"));
    }

    @Test
    void testNullPostalCode() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person("UID001", "US", null,"2000, 01, 01", "test4@domain4.com", "password7"));
    }

    @Test
    void testEmptyPostalCode() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person("UID001", "US", "", "2000, 01, 01", "test4@domain4.com", "password8"));
    }

    @Test
    void testNullDOB() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person("UID001", "US", "12345", null, "test4@domain4.com", "password7"));
    }
}