package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.Trade;

public interface ReportDao {
	List<Trade> reportqueryForAllTrades();
}
