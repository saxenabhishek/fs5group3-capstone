package com.fidelity.business;

public enum IncomeCategory {
	ZeroToTwentyK(1, "ZeroToTwentyK"), 
	TwentyKToFortyK(2, "TwentyKToFortyK"), 
	FortyKToSixtyK(3, "FortyKToSixtyK"), 
	SixtyKToEigthyK(4, "SixtyKToEigthyK"),
	EigthyKToOneL(5,  "EigthyKToOneL"),
	OneLToOneLFifty(6, "OneLToOneLFifty"),
	Above(7,"Above");
	

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
