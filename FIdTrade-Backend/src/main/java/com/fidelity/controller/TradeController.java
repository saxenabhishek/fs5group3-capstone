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
import org.springframework.web.bind.annotation.RestController;

import com.fidelity.business.Instrument;
import com.fidelity.business.Order;
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

    @GetMapping("")
    String ping() {
        logger.debug("ping()");
        return "trade is up and running";
    }

    @GetMapping("/robo-advisor/{clientID}")
    public List<Trade> getRoboAdvisor(@PathVariable String clientID) {
        List<Trade> result = tradeService.getTopBuyTrades(clientID);
        return result;
    }

    @PostMapping("/make-trade")
    ResponseEntity<Trade> makeTrade(@RequestBody Order order) {
        logger.debug("makeTrade({})", order.toString());
        Trade newTrade = service.processOrder(order);
        return ResponseEntity.ok(newTrade);
    }

    @GetMapping("/instruments")
    ResponseEntity<List<Instrument>> getAllInstrumentsFMTS() {
        logger.debug("getAllInstrumentsFMTS()");
        // return ResponseEntity.ok(new ArrayList<Instrument>(0));
        return ResponseEntity.ok(service.getAllInstruments(""));

    }
}
