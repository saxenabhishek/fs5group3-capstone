package com.fidelity.integration;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.dao.PortfolioDao;
import com.fidelity.models.Trade;

@Repository("portfolioDao")
public class PortfolioDaoImpl {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PortfolioDao portfolioDao;

//	public PortfolioDaoImpl(DataSource datasource) {
//		this.datasource = datasource;
//		try {
//			this.connection= this.datasource.getConnection();
//		}
//		catch(SQLException e) {
//			throw new DatabaseException("Cannot connect to database: ", e);
//		}
//	}
	
	public List<Trade> getPortfolio(String clientId) {
		return portfolioDao.getPortfolio(clientId);
//		String sql = "select o.clientid, t.id, o.id as orderid, o.instrumentid, o.quantity, o.direction, o.targetprice, t.executionprice, t.cashvalue, o.ordertimestamp, t.tradetimestamp "
//				+ "from ft_trade t left join ft_order o on t.clientid= o.clientid and t.orderid= o.id where t.clientid= ? and t.direction= 'B'";		
//		List<Trade> holdings = new ArrayList<>();
//		
//		try {
//		 	PreparedStatement stmt = connection.prepareStatement(sql);	
//		 	stmt.setString(1, clientId);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				String clientid = rs.getString("clientid");
//				if (clientid.equals(clientId)) {
//					String instrumentId= rs.getString("instrumentid");
//					long quantity= rs.getLong("quantity");
//					BigDecimal targetPrice= rs.getBigDecimal("targetprice");
//					String direction= rs.getString("direction");
//					String orderId= rs.getString("orderid");
//					Instant orderTimestamp= rs.getTimestamp("ordertimestamp").toInstant();
//					Order order= new Order(instrumentId, quantity, targetPrice, direction, clientid, orderId, orderTimestamp);
//					
//					String tradeId= rs.getString("id");
//					Instant tradeTimestamp= rs.getTimestamp("tradetimestamp").toInstant();
//					BigDecimal executionPrice= rs.getBigDecimal("executionprice");
//					Trade trade= new Trade (order, executionPrice, tradeId, tradeTimestamp);
//					holdings.add(trade);
//				}
//				else
//					throw new DatabaseException("Client ID doesn't match with client id fetched from database");
//			}
//			return holdings;				
//		} 
//		catch (SQLException e) {
//			logger.error("Cannot execute getPortfolio method: ", e);
//			throw new DatabaseException("Cannot execute getPortfolio method: ", e);
//		}
//		finally {
//			try {
//				connection.close();
//			}catch (SQLException e) {
//				logger.error("Cannot close the connection: ", e);
//				throw new DatabaseException("Cannot close the connection: ", e);
//			}
//		}
	}
}
