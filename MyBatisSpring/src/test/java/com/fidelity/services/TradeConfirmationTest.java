package com.fidelity.services;

import org.junit.jupiter.api.Test;

import com.fidelity.business.TradeConfirmation;

import java.math.BigDecimal;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class TradeConfirmationTest {

	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	 BigDecimal num = new BigDecimal("-100");
    @Test
    void testInvalidTradeId() {
        Date tradeDate = new Date();
        Date confirmationDate = new Date();
        TradeConfirmation tradeConfirmation = new TradeConfirmation(null, "stock", ONE_HUNDRED, "USD", tradeDate, confirmationDate);
        assertFalse(tradeConfirmation.isValidTradeConfirmation());
    }

    @Test
    void testInvalidTradeType() {
        Date tradeDate = new Date();
        Date confirmationDate = new Date();
        TradeConfirmation tradeConfirmation = new TradeConfirmation("12345", "invalidType", ONE_HUNDRED, "USD", tradeDate, confirmationDate);

        assertFalse(tradeConfirmation.isValidTradeConfirmation());
    }

    @Test
    void testInvalidTradePrice() {
        Date tradeDate = new Date();
        Date confirmationDate = new Date();
       
		TradeConfirmation tradeConfirmation = new TradeConfirmation("12345", "stock", num.negate(), "USD", tradeDate, confirmationDate);

        assertFalse(tradeConfirmation.isValidTradeConfirmation());
    }

    @Test
    void testTradeDateAfterConfirmationDate() {
        Date tradeDate = new Date();
        Date confirmationDate = new Date(System.currentTimeMillis() - 10000); // Confirmation date before trade date
        TradeConfirmation tradeConfirmation = new TradeConfirmation("12345", "stock", ONE_HUNDRED, "USD", tradeDate, confirmationDate);

        assertFalse(tradeConfirmation.isValidTradeConfirmation());
    }
    @Test
    public void testIsValidTradeTypeInvalid() {
        TradeConfirmation confirmation = new TradeConfirmation("123", "invalidtype", BigDecimal.valueOf(100.0), "USD", new Date(), new Date());
        assertFalse(confirmation.isValidTradeType());
    }

    @Test
    public void testIsValidTradeTypeValid() {
        TradeConfirmation confirmation = new TradeConfirmation("123", "stock", BigDecimal.valueOf(100.0), "USD", new Date(), new Date());
        assertTrue(confirmation.isValidTradeType());
    }


    @Test
    public void testIsTradeDateBeforeConfirmationDate() {
        Date tradeDate = new Date(System.currentTimeMillis() - 1000); // One second ago
        Date confirmationDate = new Date();
        TradeConfirmation confirmation = new TradeConfirmation("123", "stock", BigDecimal.valueOf(100.0), "USD", tradeDate, confirmationDate);
        assertTrue(confirmation.isTradeDateBeforeConfirmationDate());
    }
   
  
}
