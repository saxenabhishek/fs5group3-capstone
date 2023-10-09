package com.fidelity.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fidelity.business.Order;

@Mapper
public interface TradeHistoryMapper {
	List<Order> getAllTradeHistory();
	List<Order> getReportActivity();
}

