package com.fidelity.business;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Price {
	public BigDecimal askPrice;
	public BigDecimal bidPrice;
	public String priceTimestamp;
	String instrumentId;
	public Instrument instrument;

	public Price() {
	}

	public Price(Instrument instrument, BigDecimal askPrice, String instrumentId, BigDecimal bidPrice,
			String priceTimestamp) {
		super();
		this.askPrice = askPrice;
		this.bidPrice = bidPrice;
		this.priceTimestamp = priceTimestamp;
		this.instrumentId = instrumentId;
		this.instrument = instrument;
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

	public String getPriceTimestamp() {
		return priceTimestamp;
	}

	public void setPriceTimestamp(String priceTimestamp) {
		this.priceTimestamp = priceTimestamp;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public String getInstrumentId() {
		return instrument.instrumentId;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
}
