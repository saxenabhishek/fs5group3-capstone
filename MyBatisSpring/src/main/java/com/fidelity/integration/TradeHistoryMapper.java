package com.fidelity.integration;
import java.util.List;
import com.fidelity.business.Order;

public interface TradeHistoryMapper {
	List<Order> getAllTradeHistory();
}

