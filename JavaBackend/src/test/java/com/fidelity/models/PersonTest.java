package com.fidelity.models;

import org.junit.jupiter.api.Test;

import com.fidelity.models.Person;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class PersonTest {
	
	@Test
    void testValidPersonCreation() {
        assertDoesNotThrow(() ->
                new Person(1, "India", "12345", "01-01-2001", "test@gmail.com", "password"));
    }

    @Test
    void testNegativeId() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(-1, "US", "12345678", "01-01-2001", "test1@yahoo.com", "password1"));
    }

    @Test
    void testZeroId() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(0, "Ireland", "123", "01-01-2001", "test2@domain.com", "password2"));
    }

    @Test
    void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(3, "US", "12345678", "01-01-2001", "invalid-email", "password3"));
    }

    @Test
    void testNullEmail() {
        assertThrows(NullPointerException.class, () ->
                new Person(2, "India", "12345", "01-01-2001", null, "password4"));
    }

    @Test
    void testEmptyPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(4, "US", "12345678", "01-01-2001", "test3@domain3.com", ""));
    }

    @Test
    void testNullPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(5, "Ireland", "123", "01-01-2001", "test4@domain4.com", null));
    }

    @Test
    void testNullCountry() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(6, null, "123", "01-01-2001", "test4@domain4.com", "password5"));
    }

    @Test
    void testEmptyCountry() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(7, "", "123", "01-01-2001", "test4@domain4.com", "password6"));
    }

    @Test
    void testNullPostalCode() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(8, "US", null, "01-01-2001", "test4@domain4.com", "password7"));
    }

    @Test
    void testEmptyPostalCode() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(9, "US", "", "01-01-2001", "test4@domain4.com", "password8"));
    }

    @Test
    void testNullDOB() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(8, "US", "12345", null, "test4@domain4.com", "password7"));
    }

    @Test
    void testEmptyDOB() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(8, "US", "12345678", "", "test4@domain4.com", "password7"));
    }
}