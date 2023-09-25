package com.fidelity.business;

import java.math.BigDecimal;

public class Portfolio {
	private String instrumentId;
	private String instrumentDescription;
	private BigDecimal boughtPrice;
	private long quantity;
	private String clientId;

	public Portfolio(String instrumentId, String instrumentDescription, BigDecimal boughtPrice, long l, String string) {

		super();
		this.instrumentId = instrumentId;
		this.setInstrumentDescription(instrumentDescription);
		this.setBoughtPrice(boughtPrice);
		this.setQuantity(l);
		this.setClientId(string);

	}

	public String getInstrumentId() {

		return instrumentId;

	}

	public void setInstrumentId(String instrumentId) {

		if (this.instrumentId.length() <= 0)

			throw new IllegalArgumentException("Instrument Id can't be empty");

		this.instrumentId = instrumentId;

	}

	public String getInstrumentDescription() {

		return instrumentDescription;

	}

	public void setInstrumentDescription(String instrumentDescription) {

		if (instrumentDescription.length() <= 0)

			throw new IllegalArgumentException("Instrument Description can't be empty");

		this.instrumentDescription = instrumentDescription;

	}

	public BigDecimal getBoughtPrice() {

		return boughtPrice;

	}

	public void setBoughtPrice(BigDecimal boughtPrice) {

		if (boughtPrice.compareTo(BigDecimal.ZERO) == 0 ||

				boughtPrice.compareTo(BigDecimal.ZERO) == -1)

			throw new IllegalArgumentException("Bought Price can't be zero or negative");

		this.boughtPrice = boughtPrice;

	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		if (quantity <= 0)
			throw new IllegalArgumentException("Invalid quantity");
		this.quantity = quantity;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
