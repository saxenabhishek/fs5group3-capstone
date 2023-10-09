package com.fidelity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fidelity.business.IncomeCategory;
import com.fidelity.business.LengthOfInvestment;
import com.fidelity.business.Preference;
import com.fidelity.business.RiskTolerance;
import com.fidelity.business.Trade;


@RestController
@RequestMapping("/trade")
public class TradeController {
	@GetMapping(value="/ping")
	public String ping() {
		return "Hello";
	}
    
    @PostMapping("/register")
    ResponseEntity<Integer> registerNewClient(){
        throw new java.lang.UnsupportedOperationException();
    }
    @Autowired
    private com.fidelity.service.TradeService tradeService;

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
    
}

