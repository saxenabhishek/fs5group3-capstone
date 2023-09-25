package com.fidelity.business;


public enum RiskTolerance {
	CONSERVATIVE(1, "Conservative"), 
	BELOW_AVERAGE(2, "Below Average"), 
	AVERAGE(3, "Average"), 
	ABOVE_AVERAGE(4, "Above Average"),
	AGGRESSIVE(5,"Aggressive");

	private int code;
	private String name;

	private RiskTolerance(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static RiskTolerance of(int code) {
		for (RiskTolerance riskTolerance : values()) {
			if (riskTolerance.code == code) {
				return riskTolerance;
			}
		}
		throw new IllegalArgumentException("Unknown risk code " + code);
	}

	public static RiskTolerance of(String name) {
		for (RiskTolerance riskTolerance : values()) {
			if (name.equals(riskTolerance.name)) {
				return riskTolerance;
			}
		}
		throw new IllegalArgumentException("Unknown risk name " + name);
	}

	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}
