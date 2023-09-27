package com.fidelity.dao;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.models.Order;


 

@Repository("tradeHistoryDao")
public class TradeHistoryDaoMyBatisImpl {
	@Autowired
	private Logger logger;

	@Autowired
	private TradeHistoryMapper mapper;


	public List<Order> getAllTradeHistory() {
		logger.debug("enter");
        return mapper.getAllTradeHistory();
	}

 

	

 

}