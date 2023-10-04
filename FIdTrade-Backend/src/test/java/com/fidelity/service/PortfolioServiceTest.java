package com.fidelity.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.business.Direction;
import com.fidelity.business.Instrument;
import com.fidelity.business.Order;
import com.fidelity.business.Trade;
import com.fidelity.service.PortfolioService;


public class PortfolioServiceTest {
	private PortfolioService portfolioService;
	private List<Order> orderTemp;
	private List<Trade> tradeTemp;
	
    @BeforeEach
    public void setUp() {
        portfolioService = new PortfolioService();
        orderTemp= new ArrayList<Order>();
        tradeTemp= new ArrayList<Trade>();
        
        for (int i = 1; i <= 110; i++) {
            Order order = new Order("AAPL", 100, new BigDecimal("150.50"), Direction.BUY, "Client123", "Order00" + i, Instant.now());
            portfolioService.addTrade(order);
            orderTemp.add(order);
//            System.out.println(portfolioService.getPortfolio().get(portfolioService.getPortfolioLength()-i));
//            tradeTemp.add(portfolioService.getPortfolio().get(portfolioService.getPortfolioLength()-i));
        }
        tradeTemp.addAll(portfolioService.getPortfolio());
    }
    
    @AfterEach
	void tearDown() throws Exception {
		portfolioService= null;
		orderTemp= null;
		tradeTemp= null;
	}

    @Test
    public void testAddTrade() {
        Order order = new Order("AAPL", 100, new BigDecimal("150.50"), Direction.BUY, "Client123", "Order001", Instant.now());
        portfolioService.addTrade(order);
        assertEquals(111, portfolioService.getTradeHistoryLength());
        assertEquals(111, portfolioService.getPortfolioLength());
    }

    @Test
    public void testClientDoesNotHaveAPortfolio() {
    	PortfolioService newPS= new PortfolioService();
    	assertEquals(newPS.getPortfolio(), new ArrayList<>());
    }
    
    @Test
    public void testClientDoesNotHaveATradeHistory() {
    	PortfolioService newPS= new PortfolioService();
    	assertEquals(newPS.getTradeHistory(), new ArrayList<>());
    }

    @Test
    public void testAddTradeNullOrder() {
    	assertThrows(NullPointerException.class, () -> {
    		portfolioService.addTrade(null);
    	});
    }
    
    @Test
    public void testGetWholeTradeHistory() {   
        assertEquals(110, portfolioService.getPortfolioLength());
        assertEquals(portfolioService.getTradeHistory(), orderTemp);
    }
    
    @Test
    public void testGetTradeHistoryOnly100() {   
    	List<Order> tradeList= portfolioService.getTradeHistoryOnly100();
        assertEquals(100, tradeList.size());
    }

    @Test
    public void testGetWholePortfolio() {
        assertEquals(110, portfolioService.getPortfolioLength());
        assertEquals(portfolioService.getPortfolio(), tradeTemp);
    }
    
//    @Test
//    public void testGetInstrumentById() {
//        Instrument instrument = new Instrument("AAPL", "Apple Inc.");
//        portfolioService.getInstruments().add(instrument);
//
//        Instrument retrievedInstrument = portfolioService.getInstrumentById("AAPL");
//        assertEquals("AAPL", retrievedInstrument.getInstrumentId());
//        assertEquals("Apple Inc.", retrievedInstrument.getInstrumentName());
//    }

    @Test
    public void testGetInstrumentByIdNotFound() {
    	assertThrows(NullPointerException.class, () -> {
    		portfolioService.getInstrumentById("GOOG"); 
    	});
    }

//    @Test
//    public void testGetPriceById() {
//        
//        Instrument instrument = new Instrument("AAPL", "Apple Inc.");
//        Price price = new Price(instrument, new BigDecimal("150.50"));
//        portfolioService.getPrices().add(price);
//
//        Price retrievedPrice = portfolioService.getPriceById("AAPL");
//        assertEquals(new BigDecimal("150.50"), retrievedPrice.getPrice());
//    }

    @Test
    public void testGetPriceByIdNotFound() {
    	assertThrows(NullPointerException.class, () -> {
    		portfolioService.getPriceById("GOOG"); 
    	}); 
    }
}
