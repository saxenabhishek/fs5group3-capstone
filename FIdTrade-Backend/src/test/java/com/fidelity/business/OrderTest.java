package com.fidelity.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.business.Order;

public class OrderTest {
	private Order orderOverloaded;
	
	@BeforeEach
	void setUp() throws Exception {
		orderOverloaded = new Order("AAPL", 100, new BigDecimal("150.50"), Direction.BUY, "Client123", "Order001", Instant.now());
	}

	@AfterEach
	void tearDown() throws Exception {
		orderOverloaded = null;
	}
	
    @Test
    public void testValidOrderCreation() {        
        assertEquals("AAPL", orderOverloaded.getInstrumentId());
        assertEquals("Client123", orderOverloaded.getClientId());
        assertEquals("Order001", orderOverloaded.getOrderId());
        assertEquals(100, orderOverloaded.getQuantity());
        assertEquals(new BigDecimal("150.50"), orderOverloaded.getTargetPrice());
        assertEquals(Direction.BUY, orderOverloaded.getDirection());
    }

    @Test
    public void testOrderNullInstrumentId() {
    	assertThrows(NullPointerException.class, () -> {
    		new Order(null, 100, new BigDecimal("150.50"), Direction.BUY, "Client123", "Order001", Instant.now());
    	});
    }

    @Test
    public void testOrderEmptyInstrumentId() {
        assertThrows(IllegalArgumentException.class, () -> { 
        	new Order("", 100, new BigDecimal("150.50"), Direction.BUY, "Client123", "Order001", Instant.now());
        });
    }

    @Test
    public void testOrderNullClientId() {
    	assertThrows(NullPointerException.class, () -> { 
        	new Order("AAPL", 100, new BigDecimal("150.50"), Direction.BUY, null, "Order001", Instant.now());
        });
    }

    @Test
    public void testOrderEmptyClientId() {
    	assertThrows(IllegalArgumentException.class, () -> { 
        	new Order("AAPL", 100, new BigDecimal("150.50"), Direction.BUY, "", "Order001", Instant.now());
        });
    }

    @Test
    public void testOrderNullOrderId() {
    	assertThrows(NullPointerException.class, () -> { 
        	new Order("AAPL", 100, new BigDecimal("150.50"), Direction.BUY, "Client123", null, Instant.now());
        });
    }

    @Test
    public void testEmptyOrderId() {
    	assertThrows(IllegalArgumentException.class, () -> { 
        	new Order("AAPL", 100, new BigDecimal("150.50"), Direction.BUY, "Client123", "", Instant.now());
        });
    }

    @Test
    public void testOrderZeroOrNegativeQuantity() {
    	assertThrows(IllegalArgumentException.class, () -> { 
        	new Order("AAPL", 0, new BigDecimal("150.50"), Direction.BUY, "Client123", "Order001", Instant.now());
        	new Order("AAPL", -100, new BigDecimal("150.50"), Direction.BUY, "Client123", "Order001", Instant.now());
        });
    }

    @Test
    public void testOrderZeroOrNegativeTargetPrice() {
    	assertThrows(IllegalArgumentException.class, () -> { 
            new Order("AAPL", 100, BigDecimal.ZERO, Direction.BUY, "Client123", "Order001", Instant.now());
            new Order("AAPL", 100, new BigDecimal("-150.50"), Direction.BUY, "Client123", "Order001", Instant.now());
        });
    }

//    @Test
//    public void testOrderNullDirection() {
//    	assertThrows(NullPointerException.class, () -> { 
//            new Order("AAPL", 100, new BigDecimal("150.50"), null, "Client123", "Order001", Instant.now());
//        });
//    }
    
    @Test
    public void testOrderTimestamp() {
    	assertThrows(NullPointerException.class, () -> { 
            new Order("AAPL", 100, new BigDecimal("150.50"), Direction.BUY, "Client123", "Order001", null);
        });
    }
}

