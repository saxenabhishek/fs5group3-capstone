package com.fidelity.business;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class Price {
	private BigDecimal askPrice;
	private BigDecimal bidPrice;
	private String instrumentId;
	private LocalDateTime priceTimestamp;
	private Instrument instrument;
	
	// public Price(Instrument instrument, BigDecimal askPrice, String instrumentId ,BigDecimal bidPrice, Instant priceTimestamp) {
	// 	super();
	// 	this.askPrice = askPrice;
	// 	this.bidPrice = bidPrice;
	// 	this.priceTimestamp = priceTimestamp;
	// 	this.instrument = instrument;
	// 	this.instrumentId = instrumentId;
	// }
}
