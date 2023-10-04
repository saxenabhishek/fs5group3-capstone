package com.fidelity.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.business.Direction;
import com.fidelity.business.RoboTrade;

class RoboTradeServiceTest {

	private static RoboTrade ts;

	@BeforeEach
	void setUp() {
		ts = new RoboTrade("ETF India","IETF",2,80000,"ETF",20,Direction.BUY, Direction.SELL);
	}

	@Test
		void toGetSecurity( ) {
			String title="ETF India";
			String result = ts.getSecurityName();
            assertEquals(title, result);
		}
	@Test
	void toGetSecuritySymbol( ) {
		String title="IETF";
		String result = ts.getStockSymbol();
        assertEquals(title, result);
	}
	@Test
	void toGetSecurityType( ) {
		String title="ETF";
		String result = ts.gettradeType();
        assertEquals(title, result);
	}
	@Test
	void toBuy( ) {
		Direction result = ts.getbuy();
        assertEquals(Direction.BUY, result);
	}


}
