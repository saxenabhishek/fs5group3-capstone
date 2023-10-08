package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.Price;

public interface FMTSDao {
	boolean verifyClient();
	List<Price> getCurrentPricesFromFMTS(String category, String instrumentId);
}
