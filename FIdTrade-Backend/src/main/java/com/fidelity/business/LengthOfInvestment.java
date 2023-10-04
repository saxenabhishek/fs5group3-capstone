package com.fidelity.business;

public enum LengthOfInvestment {
	ZeroToFiveYears(1, "ZeroToFiveYears"), 
	FiveToSevenYears(2, "FiveToSevenYears"), 
	SevenToTenYears(3, "SevenToTenYears"), 
	TenToFifteenYears(4, "TenToFifteenYears");
	
	

	private int code;
	private String name;

	private LengthOfInvestment(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static LengthOfInvestment of(int code) {
		for (LengthOfInvestment length: values()) {
			if (length.code == code) {
				return length;
			}
		}
		throw new IllegalArgumentException("Unknown length code " + code);
	}

	public static LengthOfInvestment of(String name) {
		for (LengthOfInvestment length: values()) {
			if (name.equals(length.name)) {
				return length;
			}
		}
		throw new IllegalArgumentException("Unknown length name " + name);
	}

	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}


}
