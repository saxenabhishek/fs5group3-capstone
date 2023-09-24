package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.sql.DataSource;

import models.Order;
import models.Trade;
import shared.DatabaseException;

public class TradeDao {
	DataSource ds;

	public TradeDao(DataSource ds) {
		this.ds = ds;
		// TODO Auto-generated constructor stub
	}
	
	public void addTrade(Trade t) {
		String query = "INSERT INTO ft_trade (id, orderid, instrumentid, quantity, targetprice, direction, clientid, cashvalue, executionprice, tradetimestamp)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn;
		try {
			conn = ds.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, t.getTradeId());
			statement.setString(2, t.getOrder().getOrderId());
			statement.setString(3, t.getInstrumentId());
			statement.setLong(4, t.getQuantity());
			statement.setBigDecimal(5, t.getOrder().getTargetPrice());
			statement.setString(6, t.getDirection().getStringValue());
			statement.setString(7, t.getClientId());
			statement.setBigDecimal(8, t.getCashValue());
			statement.setBigDecimal(9, t.getExecutionPrice());
			
			LocalDateTime ldt = LocalDateTime.ofInstant( t.getTimestamp(), ZoneOffset.UTC);
			Timestamp ts = Timestamp.valueOf(ldt);
	
			
			statement.setTimestamp(10, ts);
			
			System.out.println(statement.executeUpdate());
			
		} catch (SQLException e) {
			throw new DatabaseException("Could not add a trade to db", e);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	INSERT INTO ft_order (id, instrumentid, quantity, targetprice, direction, clientid, ordertimestamp) 
//	VALUES ('OID001', 'Q123', 50, 1161.42, 'B', 'UID001', TIMESTAMP '2023-09-20 10:30:00');
	
	public void addOrder(Order o) {
		String query = "INSERT INTO ft_order (id, instrumentid, quantity, targetprice, direction, clientid, ordertimestamp)"
				+ "	VALUES (?, ?,?,?, ?,?,?)";
		
		Connection conn;
		try {
			conn = ds.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, o.getOrderId());
			statement.setString(2, o.getInstrumentId());
			statement.setLong(3, o.getQuantity());
			statement.setBigDecimal(4, o.getTargetPrice());
			statement.setString(5, o.getDirection().getStringValue());
			statement.setString(6, o.getClientId());
			
			LocalDateTime ldt = LocalDateTime.ofInstant( o.getTimestamp(), ZoneOffset.UTC);
			Timestamp ts = Timestamp.valueOf(ldt);

			statement.setTimestamp(7, ts);
			
			System.out.println(statement.executeUpdate());
			
		} catch (SQLException e) {
			throw new DatabaseException("Could not add a order to db", e);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
