package com.fidelity.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import com.fidelity.business.Order;
import com.fidelity.business.Price;
import com.fidelity.business.Trade;
import com.fidelity.integration.FMTSDao;
import com.fidelity.service.PortfolioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    FMTSDao fmtsDao;
    
    @Autowired
    Logger logger;
    
    @Autowired
    PortfolioService portfolioService;
    
	@GetMapping("/prices")
	ResponseEntity<List<Price>> getCurrentPricesOfInstruments(
			@RequestParam(name = "category", required = false) String category,
	        @RequestParam(name = "instrumentId", required = false) String instrumentId
	    ){
		try {
			List<Price> instrumentPrices= portfolioService.retrieveCurrentInstrumentPrices(category, instrumentId);
			if (instrumentPrices.size() == 0)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			else
				return ResponseEntity.status(HttpStatus.OK).body(instrumentPrices);
		}
		catch (RuntimeException e) {
			throw new ServerErrorException("Server side error", e);
		}
    }
	
    @GetMapping("/holdings")
    ResponseEntity<List<Trade>> getCurrentPortfolioHoldings(@RequestParam String clientId){
    	try {
			List<Trade> allHoldings= portfolioService.retrieveCurrentHoldings(clientId);
			if (allHoldings.size() == 0)
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			else
				return ResponseEntity.status(HttpStatus.OK).body(allHoldings);
		}
		catch (RuntimeException e) {
			throw new ServerErrorException("Server side error", e);
		}
    }
    
    @GetMapping("/trade-history")
    ResponseEntity<List<Order>> getTradeHistory(@RequestParam String clientId){
    	try {
			List<Order> tradeHistory= portfolioService.retrieveTradeHistory(clientId);
			if (tradeHistory.size() == 0)
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			else
				return ResponseEntity.status(HttpStatus.OK).body(tradeHistory);
		}
		catch (RuntimeException e) {
			throw new ServerErrorException("Server side error", e);
		}
    }
}

