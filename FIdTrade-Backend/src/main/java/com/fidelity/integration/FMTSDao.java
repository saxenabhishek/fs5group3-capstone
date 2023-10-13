package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.Client;
import com.fidelity.business.ClientFMTS;
import com.fidelity.business.Price;

public interface FMTSDao {
	List<Price> getCurrentPricesFromFMTS(String category, String instrumentId);
	ClientFMTS verifyClientInformation(Client client);
}
