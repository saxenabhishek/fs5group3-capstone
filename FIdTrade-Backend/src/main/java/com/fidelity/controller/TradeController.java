package com.fidelity.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fidelity.business.IncomeCategory;
import com.fidelity.business.Instrument;
import com.fidelity.business.LengthOfInvestment;
import com.fidelity.business.Order;
import com.fidelity.business.Preference;
import com.fidelity.business.RiskTolerance;
import com.fidelity.business.Trade;
import com.fidelity.service.TradeService;

@RestController
@RequestMapping("/trade")
@CrossOrigin(origins = "http://localhost:4200")
public class TradeController {

    @Autowired
    Logger logger;

    @Autowired
    TradeService service;

    @Autowired
    private com.fidelity.service.TradeService tradeService;
    
    @PostMapping("/register")
    ResponseEntity<Integer> registerNewClient(){
        throw new java.lang.UnsupportedOperationException();
    }
    

    @PostMapping("/top-trades")
    public List<Trade> roboAdvisor(@RequestBody Preference preferences) {
    	Preference p = new Preference("client1", "Personal", RiskTolerance.ABOVE_AVERAGE, IncomeCategory.ZeroToTwentyK, LengthOfInvestment.ZeroToFiveYears, "T");
       	String clientId = "client12";
		List<Trade> topTrades = tradeService.getTopBuyTrades(clientId);
        return topTrades;
    }
    
    @GetMapping("/robo-advisor/{clientID}")
    public List<Trade> getRoboAdvisor(@PathVariable String clientID){
    	List<Trade> result = tradeService.getTopBuyTrades(clientID);
    	return result;
    }
    

    @GetMapping("")
    String ping(){
        logger.debug("ping()");
        return "trade is up and running";
    }

    @GetMapping("/echo")
    String echo(@RequestParam(defaultValue = " ") String param ){
        logger.debug("echo({})", param);
        return param;
    }

    @PostMapping("/make-trade")
    ResponseEntity<Trade> makeTrade(@RequestBody Order order){
        logger.debug("makeTrade({})", order.toString());
        Trade newTrade = service.processOrder(order);
        return ResponseEntity.ok(newTrade);
    }

    @GetMapping("/instruments")
    ResponseEntity<List<Instrument>> getAllInstrumentsFMTS(){
        logger.debug("getAllInstrumentsFMTS()");
        // return ResponseEntity.ok(new ArrayList<Instrument>(0));
        return ResponseEntity.ok(service.getAllInstruments(""));

    }
}

