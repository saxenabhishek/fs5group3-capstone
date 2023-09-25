package com.fidelity.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.business.Order;
import com.fidelity.business.Trade;

public class TradeTest {
	private Order orderOverloaded;
	private Trade tradeOverloaded;
	
	@BeforeEach
	void setUp() throws Exception {
		orderOverloaded = new Order("AAPL", 100, new BigDecimal("150.50"), "B", "Client123", "Order001", Instant.now().minusSeconds(60));
		tradeOverloaded = new Trade(orderOverloaded, new BigDecimal("151.50"), "Trade001", Instant.now());
	}

	@AfterEach
	void tearDown() throws Exception {
		orderOverloaded= null;
		tradeOverloaded = null;
	}
	
    @Test
    public void testValidTradeCreation() {        
        assertEquals(orderOverloaded, tradeOverloaded.getOrder());
        assertEquals("Client123", tradeOverloaded.getClientId());
        assertEquals("Trade001", tradeOverloaded.getTradeId());
        assertEquals(100, tradeOverloaded.getQuantity());
        assertEquals(new BigDecimal("151.50"), tradeOverloaded.getExecutionPrice());
        assertEquals(new BigDecimal("15152.00"), tradeOverloaded.getCashValue());
        assertEquals("B", tradeOverloaded.getDirection().getStringValue());
    }
    
    @Test
    public void testNullOrder() {
    	assertThrows(NullPointerException.class, () -> { 
        	new Trade(null, new BigDecimal("151.50"), null, Instant.now());
        });
    }

    @Test
    public void testNullTradeId() {
    	assertThrows(NullPointerException.class, () -> { 
        	new Trade(orderOverloaded, new BigDecimal("151.50"), null, Instant.now());
        });
    }

    @Test
    public void testEmptyTradeId() {
    	assertThrows(IllegalArgumentException.class, () -> { 
        	new Trade(orderOverloaded, new BigDecimal("151.50"), "", Instant.now());
        });
    }

    @Test
    public void testTradeZeroOrNegativeExecutionPrice() {
    	assertThrows(IllegalArgumentException.class, () -> { 
            new Trade(orderOverloaded, new BigDecimal("-150.50"), "Trade001", Instant.now());
            new Trade(orderOverloaded, BigDecimal.ZERO, "Trade001", Instant.now());
        });
    }
    
    @Test
    public void testTradeNullTimestamp() {
    	assertThrows(NullPointerException.class, () -> { 
        	new Trade(orderOverloaded, new BigDecimal("151.50"), "Trade001", null);
        });
    }
}
