package models;

import java.math.BigDecimal;
import java.time.Instant;

public class Order {
	private String instrumentId, clientId, orderId;
	private long quantity;
	private BigDecimal targetPrice;
	private Direction direction;
	private Instant timestamp;
	
	public Order (String instrumentId, long quantity, BigDecimal targetPrice, String direction,
					String clientId, String orderId, Instant timestamp) 
	{
		if (instrumentId == null)
			throw new NullPointerException ("Instrument ID can't be null");
		if (instrumentId == "")
			throw new IllegalArgumentException ("Instrument ID can't be empty");
		this.instrumentId = instrumentId;
		
		if (clientId == null)
			throw new NullPointerException ("Client ID can't be null");
		if (clientId == "")
			throw new IllegalArgumentException ("Client ID can't be empty");
		this.clientId = clientId;
		
		if (orderId == null)
			throw new NullPointerException ("Order ID can't be null");
		if (orderId == "")
			throw new IllegalArgumentException ("Order ID can't be empty");
		this.orderId = orderId;
		
		if (quantity <= 0)
			throw new IllegalArgumentException ("Order quantity can't be zero or negative");		
		this.quantity = quantity;
		
		if (targetPrice.compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException ("Target price of order can't be zero or negative");	
		this.targetPrice = targetPrice;
		
		if ("B".equals(direction)) 
            this.direction = Direction.BUY;
        else if ("S".equals(direction)) 
            this.direction = Direction.SELL;
        else if (direction == null)
            throw new NullPointerException("Order direction value can't be null");
        else
            throw new IllegalArgumentException("Invalid order direction value! Only B(for Buy) or S (for Sell)");
	
		if (timestamp== null)
			throw new NullPointerException ("Order timestamp can't be null");
		this.timestamp= timestamp;
	}

	public String getInstrumentId() {
		return this.instrumentId;
	}

	public String getClientId() {
		return this.clientId;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public BigDecimal getTargetPrice() {
		return this.targetPrice;
	}

	public Direction getDirection() {
		return this.direction;
	}	
	
	public Instant getTimestamp() {
		return this.timestamp;
	}
}
