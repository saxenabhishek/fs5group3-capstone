package com.fidelity.business;

public enum Direction {
    BUY("BUY"),
    SELL("SELL");

    private String stringValue;

    private Direction(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
    
    public static Direction of(String code) {
		for (Direction d: values()) 
			if (d.stringValue.equals(code)) 
				return d;
		
		throw new IllegalArgumentException("Unknown Trade Direction! Only BUY or SELL.");
	}
}

