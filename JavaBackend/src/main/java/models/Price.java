package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {
	public BigDecimal askPrice;
	public BigDecimal bidPrice;
	public String instrumentId;
	public LocalDateTime priceTimestamp;
	public Instrument instrument;
	
	public Price(Instrument instrument, BigDecimal askPrice, String instrumentId ,BigDecimal bidPrice, LocalDateTime priceTimestamp) {
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

	public LocalDateTime getPriceTimestamp() {
		return priceTimestamp;
	}

	public void setPriceTimestamp(LocalDateTime priceTimestamp) {
		this.priceTimestamp = priceTimestamp;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
}
