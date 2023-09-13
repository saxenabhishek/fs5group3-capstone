package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    void testValidClientCreation() {
        Person person = new Person(1, "US", "12345", "1990-01-01", "test@example.com", "password");
        ClientIdentification identification = new ClientIdentification("Passport", "AB123456");

        assertDoesNotThrow(() ->
                new Client(person, identification));
    }

    @Test
    void testNullPerson() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(null, new ClientIdentification("Passport", "AB123456")));
    }

    @Test
    void testNullClientIdentification() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person(1, "US", "12345", "1990-01-01", "test@example.com","password"),null));
    }

    @Test
    void testNullClient() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(null,null));
    }

    @Test
    void testEmptyClientIdentification() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person(1, "US", "12345", "1990-01-01", "test@example.com", "password"), new ClientIdentification("", "")));
    }
    
    void testEmptyPerson() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person(1,"","","","",""), new ClientIdentification("Passport", "AB123456")));
    }
    @Test
    void testEmptyClient() {
        assertThrows(IllegalArgumentException.class, () ->
                new Client(new Person(1,"","","","",""), new ClientIdentification("", "")));
    }  
}
