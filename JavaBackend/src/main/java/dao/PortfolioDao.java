package dao;

import java.util.List;
import models.Trade;

public interface PortfolioDao {
	List<Trade> getPortfolio(String clientId);
}
