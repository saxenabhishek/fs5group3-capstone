package com.fidelity.business;


import java.util.Objects;

public class Preference {
	
	private String id;
    private String investmentPurpose;
	private RiskTolerance riskTolerance;
	private IncomeCategory incomeCategory;
	private LengthOfInvestment lengthOfInvestmet;
	private String isChecked;

	public Preference() {}

	public Preference(String id, String investmentPurpose, RiskTolerance riskTolerance, IncomeCategory incomeCategory, LengthOfInvestment lengthOfInvestmet, 
			   String isChecked) {
		super();
		this.setId(id);
		this.setInvestmentPurpose(investmentPurpose);
		this.setRiskTolerance(riskTolerance);
		this.setIncomeCategory(incomeCategory);
		this.setLengthOfInvestmet(lengthOfInvestmet);
		this.setIsChecked(isChecked);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvestmentPurpose() {
		return investmentPurpose;
	}

	public void setInvestmentPurpose(String investmentPurpose) {
		this.investmentPurpose = investmentPurpose;
	}

	public RiskTolerance getRiskTolerance() {
		return riskTolerance;
	}

	public void setRiskTolerance(RiskTolerance riskTolerance) {
		this.riskTolerance = riskTolerance;
	}

	public IncomeCategory getIncomeCategory() {
		return incomeCategory;
	}

	public void setIncomeCategory(IncomeCategory incomeCategory) {
		this.incomeCategory = incomeCategory;
	}

	public LengthOfInvestment getLengthOfInvestmet() {
		return lengthOfInvestmet;
	}

	public void setLengthOfInvestmet(LengthOfInvestment lengthOfInvestmet) {
		this.lengthOfInvestmet = lengthOfInvestmet;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, incomeCategory, investmentPurpose, isChecked, lengthOfInvestmet, riskTolerance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preference other = (Preference) obj;
		return Objects.equals(id, other.id) && incomeCategory == other.incomeCategory
				&& Objects.equals(investmentPurpose, other.investmentPurpose)
				&& Objects.equals(isChecked, other.isChecked) && lengthOfInvestmet == other.lengthOfInvestmet
				&& riskTolerance == other.riskTolerance;
	}



}
