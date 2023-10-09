package com.fidelity.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode
@ToString
public class Trade {
	private Order order;
	private BigDecimal cashValue, executionPrice;
	private long quantity;
	private Direction direction;
	private String instrumentId, clientId, tradeId;
	private Instant tradeTimestamp;
	
	public Trade() { }
	
	public Trade(Order order, BigDecimal executionPrice, String tradeId, Instant timestamp) {
		
		if (order == null)
			throw new NullPointerException ("Order can't be null");
		this.order = order;		
		
		if (executionPrice.compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException ("Execution price of order can't be zero or negative");	
		this.executionPrice = executionPrice;
		
		this.quantity = order.getQuantity();
		this.cashValue = executionPrice.multiply(new BigDecimal(quantity))
								.add(new BigDecimal("2.0")).setScale(2, RoundingMode.HALF_UP);		
        this.direction = order.getDirection();
		this.instrumentId = order.getInstrumentId();
		this.clientId = order.getClientId();
		
		if (tradeId == null)
			throw new NullPointerException ("Trade ID can't be null");
		if (tradeId == "")
			throw new IllegalArgumentException ("Trade ID can't be empty");
		this.tradeId = tradeId;
		
		if (timestamp== null)
			throw new NullPointerException ("Trade timestamp can't be null");
		this.tradeTimestamp= timestamp;
	}

	public Order getOrder() {
		return this.order;
	}

	public BigDecimal getCashValue() {
		return this.cashValue;
	}

	public BigDecimal getExecutionPrice() {
		return this.executionPrice;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public String getInstrumentId() {
		return this.instrumentId;
	}

	public String getClientId() {
		return this.clientId;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public Instant getTradeTimestamp() {
		return this.tradeTimestamp;
	}
}
