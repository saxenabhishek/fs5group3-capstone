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

	public Double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(Double askPrice) {
		this.askPrice = askPrice;
	}

	public Double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Double bidPrice) {
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
