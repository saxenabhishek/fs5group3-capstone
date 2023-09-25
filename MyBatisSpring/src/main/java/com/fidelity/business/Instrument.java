package com.fidelity.business;

public class Instrument {
    private String description;
    private String externalId;
    private int maxQuantity;
    private String instrumentId;
    private int minQuantity;
    private String categoryId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExternalId() {
		return externalId;
	}

 	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

 

	public int getMaxQuantity() {
		return maxQuantity;
	}

 

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
 

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

 

	public int getMinQuantity() {
		return minQuantity;
	}

 

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

 

	public String getCategoryId() {
		return categoryId;
	}

 

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

 

	public Instrument(String description, String externalId, int maxQuantity, String instrumentId, int minQuantity,
			String categoryId) {
		super();
		this.description = description;
		this.externalId = externalId;
		this.maxQuantity = maxQuantity;
		this.instrumentId = instrumentId;
		this.minQuantity = minQuantity;
		this.categoryId = categoryId;
	}
	
	
	public String getInstrumentId() {
		return this.instrumentId;
	}
}

