package services;

import models.RoboTrade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoboTradeService {

	public static void main(String[] args) {
        // Create a list of objects representing stocks
        List<RoboTrade> topStocks = new ArrayList<>();
        topStocks.add(new RoboTrade("FidelityMF","FMF",12,50000,"Mutual Fund",20,"BUY","SELL"));
        topStocks.add(new RoboTrade("Microsoft","MSFT",9,30000,"Stock",20,"BUY","SELL"));
        topStocks.add(new RoboTrade("Google","GOOG",11,70000,"Stock",20,"BUY","SELL"));
        topStocks.add(new RoboTrade("Apple","APPL",2,80000,"ETF",20,"BUY","SELL"));
        topStocks.add(new RoboTrade("Paypal","PAY",12,50000,"Mutual Fund",20,"BUY","SELL"));
        topStocks.add(new RoboTrade("Amazon","AMZ",11,70000,"Stock",20,"BUY","SELL"));
        topStocks.add(new RoboTrade("ETF India","IETF",2,80000,"ETF",20,"BUY","SELL"));

        // Shuffle the collection
        Collections.shuffle(topStocks);

        // Display the top 5 
        System.out.println("Top 5 stocks:");
        for (int i = 0; i < Math.min(5, topStocks.size()); i++) {
            RoboTrade stock = topStocks.get(i);
            System.out.println("Security Name: " + stock.getSecurityName());
            System.out.println("Stock Symbol: " + stock.getStockSymbol());
            // Add other fields as needed
            System.out.println();
        }
    }
}