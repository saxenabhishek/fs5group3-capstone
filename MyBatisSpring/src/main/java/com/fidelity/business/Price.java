package com.fidelity.business;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Price {
	public BigDecimal askPrice;
	public BigDecimal bidPrice;
	public String instrumentId;
	public LocalDate priceTimestamp;
	public Instrument instrument;
	
	public Price(Instrument instrument, BigDecimal askPrice, String instrumentId ,BigDecimal bidPrice, LocalDate priceTimestamp) {
		super();
		this.askPrice = askPrice;
		this.bidPrice = bidPrice;
		this.priceTimestamp = priceTimestamp;
		this.instrument = instrument;
		this.instrumentId = instrumentId;
	}

	public BigDecimal getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(BigDecimal askPrice) {
		this.askPrice = askPrice;
	}

	public BigDecimal getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public LocalDate getPriceTimestamp() {
		return priceTimestamp;
	}

	public void setPriceTimestamp(LocalDate priceTimestamp) {
		this.priceTimestamp = priceTimestamp;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	@Override
	public String toString() {
		return "Price [askPrice=" + askPrice + ", bidPrice=" + bidPrice + ", instrumentId=" + instrumentId
				+ ", priceTimestamp=" + priceTimestamp + ", instrument=" + instrument + "]";
	}
}
