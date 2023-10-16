package com.fidelity.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @AllArgsConstructor

@NoArgsConstructor
@ToString
@Getter
@Setter
public class Instrument {
	String instrumentId;
	String externalId;
	String externalIdType;
	String instrumentDescription;
	String categoryId;
	long maxQuantity;
	long minQuantity;

	public Instrument(String instrumentId, String externalId, String externalIdType, String instrumentDescription,
			String categoryId, long maxQuantity, long minQuantity) {
		super();
		this.instrumentId = instrumentId;
		this.externalId = externalId;
		this.externalIdType = externalIdType;
		this.instrumentDescription = instrumentDescription;
		this.categoryId = categoryId;
		this.maxQuantity = maxQuantity;
		this.minQuantity = minQuantity;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public String getExternalId() {
		return externalId;
	}

	public String getExternalIdType() {
		return externalIdType;
	}

	public String getInstrumentDescription() {
		return instrumentDescription;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public long getMaxQuantity() {
		return maxQuantity;
	}

	public long getMinQuantity() {
		return minQuantity;
	}

}
