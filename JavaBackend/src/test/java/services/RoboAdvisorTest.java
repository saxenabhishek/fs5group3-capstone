import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoboTradeServiceTest {

	private static RoboTrade ts;

	@BeforeEach
	void setUp() {
		ts = new RoboTrade("ETF India","IETF",2,80000,"ETF",20,"BUY","SELL");
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
		String title="BUY";
		String result = ts.getbuy();
        assertEquals(title, result);
	}


}
