package com.fidelity.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Instrument {
    String instrumentId;
    String externalId;
    String externalIdType;
    String description;
    String categoryId;
    long maxQuantity;
    long minQuantity;

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
