package models;

import java.math.BigDecimal;

public class Portfolio {
	private String instrumentId;
	private String instrumentDescription;
	private BigDecimal boughtPrice;
	private BigDecimal totalInvestment;
	private int quantity;
	private long clientId;

	public Portfolio(String instrumentId, String instrumentDescription, BigDecimal boughtPrice,
			BigDecimal totalInvestment, int quantity, long clientId) {

		super();
		this.setInstrumentId(instrumentId);
		this.setInstrumentDescription(instrumentDescription);
		this.setBoughtPrice(boughtPrice);
		this.setTotalInvestment(totalInvestment);
		this.setQuantity(quantity);
		this.setClientId(clientId);

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

		if (this.instrumentDescription.length() <= 0)

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

	public BigDecimal getTotalInvestment() {

		return totalInvestment;

	}

	public void setTotalInvestment(BigDecimal totalInvestment) {

		if (totalInvestment.compareTo(BigDecimal.ZERO) == 0 ||

				totalInvestment.compareTo(BigDecimal.ZERO) == -1)

			throw new IllegalArgumentException("Bought Price can't be zero or negative");

		this.totalInvestment = totalInvestment;

	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity <= 0)
			throw new IllegalArgumentException("Invalid quanity");
		this.quantity = quantity;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
}
