package com.fidelity.models;

public enum Direction {
    BUY("B"),
    SELL("S");

    private final String stringValue;

    private Direction(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}

