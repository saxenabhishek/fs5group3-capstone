package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class PersonTest {
	
    @Test
    void testValidPersonCreation() {
        assertDoesNotThrow(() ->
                new Person(1, "US", "12345", "1990-01-01", "test@example.com", "password"));
    }

    @Test
    void testNegativeId() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(-1, "US", "12345", "1990-01-01", "test@example.com", "password"));
    }

    @Test
    void testZeroId() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(0, "US", "12345", "1990-01-01", "test@example.com", "password"));
    }
    
    @Test
    void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(1, "US", "12345", "1990-01-01", "invalid-email", "password"));
    }

    @Test
    void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(2, "US", "12345", "1990-01-01", null, "password"));
    }

    @Test
    void testEmptyPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(1, "US", "12345", "1990-01-01", "test@example.com", ""));
    }
    
    @Test
    void testNullPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                new Person(1, "US", "12345", "1990-01-01", "test@example.com", null));
    }
}