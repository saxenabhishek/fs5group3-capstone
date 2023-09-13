package models;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientIdentificationTest {

    @Test
    void testValidClientIdentificationCreation() {
        assertDoesNotThrow(() ->
                new ClientIdentification("Passport", "AB123456"));
    }
    
    @Test
    void testNullType() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClientIdentification(null, "AB123456"));
    }

    @Test
    void testEmptyType() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClientIdentification("", "AB123456"));
    }

    @Test
    void testEmptyValue() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClientIdentification("Passport", ""));
    }
    
    @Test
    void testNullValue() {
        assertThrows(NullPointerException.class, () ->
                new ClientIdentification("Passport", null));
    }
}