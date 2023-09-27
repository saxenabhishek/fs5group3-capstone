package com.fidelity.business;
import org.junit.jupiter.api.Test;

import com.fidelity.business.ClientIdentification;

import static org.junit.jupiter.api.Assertions.*;

public class ClientIdentificationTest {

	@Test
    void testValidClientIdentificationCreation() {
        assertDoesNotThrow(() ->
                new ClientIdentification(2345678, "UID001", "Passport", "AB1234567"));
    }

    @Test
    void testNullType() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClientIdentification(2345678, "UID001", null, "AB123"));
    }

    @Test
    void testEmptyType() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClientIdentification(2345678, "UID001", "", "AB123456789"));
    }

    @Test
    void testEmptyValue() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClientIdentification(2345678, "UID001", "Passport", ""));
    }

    @Test
    void testNullValue() {
        assertThrows(NullPointerException.class, () ->
                new ClientIdentification(2345678, "UID001", "Passport", null));
    }
}