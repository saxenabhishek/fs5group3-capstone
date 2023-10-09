package com.fidelity.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.business.Instrument;
import com.fidelity.business.Order;
import com.fidelity.business.Price;
import com.fidelity.business.Trade;
import com.fidelity.mapper.TradeMapper;

@Repository("tradeDao")
public class TradeDao {
    @Autowired
    TradeMapper tradeMapper;

    public int addTrade(Trade t) {
        return tradeMapper.addTrade(t);
    }

    public int addOrder(Order o) {
        return tradeMapper.addOrder(o);
    }

    public List<Instrument> getAllInstruments() {
        return tradeMapper.getAllInstruments();
    }

    public List<Instrument> getInstrumentById(String InstrumentId) {
        return tradeMapper.getInstrumentById(InstrumentId);
    }

	public  List<Price> getfulPriceTable(){
        return tradeMapper.getfulPriceTable();
    }

    public List<Price> getAllPrices() {
        return tradeMapper.getAllLatestPrices();
    }

	public List<Price> getPricesById(String priceId) {
		return tradeMapper.getPriceById(priceId);
	}
}
