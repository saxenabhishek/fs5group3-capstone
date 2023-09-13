package models;

import java.time.LocalDateTime;

public class Price {
	public Double askPrice;
	public Double bidPrice;
	public String instrumentId;
	public LocalDateTime priceTimestamp;
	public Instrument instrument;
	
	public Price(Instrument instrument, Double askPrice, String instrumentId ,Double bidPrice, LocalDateTime priceTimestamp) {
		super();
		this.askPrice = askPrice;
		this.bidPrice = bidPrice;
		this.priceTimestamp = priceTimestamp;
		this.instrument = instrument;
		this.instrumentId = instrumentId;
	}
}
