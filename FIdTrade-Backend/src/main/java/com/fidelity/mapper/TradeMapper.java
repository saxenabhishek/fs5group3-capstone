package com.fidelity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.fidelity.business.Instrument;
import com.fidelity.business.Order;
import com.fidelity.business.Price;
import com.fidelity.business.Trade;

@Mapper
public interface TradeMapper {
	@Insert("INSERT INTO ft_trade (id, orderid, instrumentid, quantity, targetprice, direction, clientid, cashvalue, executionprice, tradetimestamp) "+
	"VALUES (#{tradeId}, #{order.orderId}, #{instrumentId}, #{quantity}, #{order.targetPrice}, #{direction}, #{clientId}, #{cashValue}, #{executionPrice}, #{tradeTimestamp})")
	public int addTrade(Trade t);
	
	@Insert("INSERT INTO ft_order (id, instrumentid, quantity, targetprice, direction, clientid, ordertimestamp) "+
	" VALUES (#{orderId}, #{instrumentId}, #{quantity}, #{targetPrice}, #{direction}, #{clientId}, #{orderTimestamp})")
	public int addOrder(Order o);

	@Select("SELECT id instrumentId, externalid, categoryid, instrumentdescription description, maxquantity, minquantity FROM ft_instrument")
    public List<Instrument> getAllInstruments();

	@Select("SELECT id instrumentId, externalid, categoryid, instrumentdescription description, maxquantity, minquantity FROM ft_instrument WHERE instrumentId = #{id}")
    public List<Instrument> getInstrumentById(String id);


	public List<Price> getAllLatestPrices();

	public List<Price> getPriceById(String priceId);

}
