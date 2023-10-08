package com.fidelity.business;

import java.util.Objects;

public class Instrument {
    private String instrumentDescription;
    private String externalId;
    private int maxQuantity;
    private String instrumentId;
    private int minQuantity;
    private String categoryId;
    private String externalIdType;

   	public Instrument() {
   		super();
   	}
   	
   	public Instrument(String description, String externalId, int maxQuantity, String instrumentId, int minQuantity,
   			String categoryId, String externalIdType) {
   		super();
   		this.instrumentDescription = description;
   		this.externalId = externalId;
   		this.maxQuantity = maxQuantity;
   		this.instrumentId = instrumentId;
   		this.minQuantity = minQuantity;
   		this.categoryId = categoryId;
   		this.externalIdType= externalIdType;
   	}   	
   	
	public String getExternalIdType() {
		return externalIdType;
	}

	public void setExternalIdType(String externalIdType) {
		this.externalIdType = externalIdType;
	}	

	public String getInstrumentDescription() {
		return instrumentDescription;
	}

	public void setInstrumentDescription(String instrumentDescription) {
		this.instrumentDescription = instrumentDescription;
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

	public String getInstrumentId() {
		return this.instrumentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, externalId, externalIdType, instrumentDescription, instrumentId, maxQuantity,
				minQuantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instrument other = (Instrument) obj;
		return Objects.equals(categoryId, other.categoryId) && Objects.equals(externalId, other.externalId)
				&& Objects.equals(externalIdType, other.externalIdType)
				&& Objects.equals(instrumentDescription, other.instrumentDescription)
				&& Objects.equals(instrumentId, other.instrumentId) && maxQuantity == other.maxQuantity
				&& minQuantity == other.minQuantity;
	}

	@Override
	public String toString() {
		return "Instrument [instrumentDescription=" + instrumentDescription + ", externalId=" + externalId
				+ ", maxQuantity=" + maxQuantity + ", instrumentId=" + instrumentId + ", minQuantity=" + minQuantity
				+ ", categoryId=" + categoryId + ", externalIdType=" + externalIdType + "]";
	}
	
}

