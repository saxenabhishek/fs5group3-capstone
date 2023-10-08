package com.fidelity.business;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {
	public BigDecimal askPrice;
	public BigDecimal bidPrice;
	public String priceTimestamp;
	public Instrument instrument;
	
	public Price(Instrument instrument, BigDecimal askPrice, String instrumentId ,BigDecimal bidPrice, String priceTimestamp) {
		super();
		this.askPrice = askPrice;
		this.bidPrice = bidPrice;
		this.priceTimestamp = priceTimestamp;
		this.instrument = instrument;
	}	

	public Price() {}
	
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

	public String getPriceTimestamp() {
		return priceTimestamp;
	}

	public void setPriceTimestamp(String priceTimestamp) {
		this.priceTimestamp = priceTimestamp;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(askPrice, bidPrice, instrument, priceTimestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		return Objects.equals(askPrice, other.askPrice) && Objects.equals(bidPrice, other.bidPrice)
				&& Objects.equals(instrument, other.instrument) && Objects.equals(priceTimestamp, other.priceTimestamp);
	}

	@Override
	public String toString() {
		return "Price [askPrice=" + askPrice + ", bidPrice=" + bidPrice + ", priceTimestamp=" + priceTimestamp
				+ ", instrument=" + instrument + "]";
	}	
}
