package com.fidelity.models;

public class Preference {
	private String investmentPurpose;
	private String riskTolerance;
	private String incomeCategory;
	private String lengthOfInvestmet;
	private boolean isAccept;
	
	public Preference(String investmentPurpose,String riskTolerance,String incomeCategory,String lengthOfInvestmet,boolean isAccept) {
		this.setInvestmentPurpose(investmentPurpose);
		this.setRiskTolerance(riskTolerance);
		this.setIncomeCategory(incomeCategory);
		this.setLengthOfInvestmet(lengthOfInvestmet);
		this.isAccept=isAccept;
		
	}

	public String getInvestmentPurpose() {
		return investmentPurpose;
	}

	public void setInvestmentPurpose(String investmentPurpose) {
		this.investmentPurpose = investmentPurpose;
	}

	public String getRiskTolerance() {
		return riskTolerance;
	}

	public void setRiskTolerance(String riskTolerance) {
		this.riskTolerance = riskTolerance;
	}

	public String getIncomeCategory() {
		return incomeCategory;
	}

	public void setIncomeCategory(String incomeCategory) {
		this.incomeCategory = incomeCategory;
	}

	public String getLengthOfInvestmet() {
		return lengthOfInvestmet;
	}

	public void setLengthOfInvestmet(String lengthOfInvestmet) {
		this.lengthOfInvestmet = lengthOfInvestmet;
	}

	public boolean isAccept() {
		return isAccept;
	}

	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}
	
	
}
