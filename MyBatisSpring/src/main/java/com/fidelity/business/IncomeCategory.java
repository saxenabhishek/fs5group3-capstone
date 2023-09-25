package com.fidelity.business;

public enum IncomeCategory {
	ZeroToTwentyK(1, "0 - 20,000"), 
	TwentyKToFortyK(2, "20,001 - 40,000"), 
	FortyKToSixtyK(3, "40,001 - 60,000"), 
	SixtyKToEigthyK(4, "60,001 - 80,000"),
	EigthyKToOneL(5,  "80,001 - 100,000"),
	OneLToOneLFifty(6, "100,001 - 150,000"),
	Above(7,"150,000+");
	

	private int code;
	private String name;

	private IncomeCategory(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static IncomeCategory of(int code) {
		for (IncomeCategory category : values()) {
			if (category.code == code) {
				return category;
			}
		}
		throw new IllegalArgumentException("Unknown category code " + code);
	}

	public static IncomeCategory of(String name) {
		for (IncomeCategory category : values()) {
			if (name.equals(category.name)) {
				return category;
			}
		}
		throw new IllegalArgumentException("Unknown category name " + name);
	}

	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

}
