package com.fidelity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/reports")
public class ReportController {
//    @Autowired
//    private ReportService reportService;
//
//    @GetMapping("/generate")
//    public ResponseEntity<Report> generateReport() {
//        Report report = reportService.generateReport();
//        return ResponseEntity.ok(report);
//    }
}


    // @PostMapping("/confirm")
    // public ResponseEntity<Void> confirmTradeOrder(@RequestBody TradeOrder tradeOrder) {
    //     // Process the trade order and determine if it's successful.
    //     boolean isSuccessful = yourTradeConfirmationLogic(tradeOrder);

    //     tradeOrder.setSuccessful(isSuccessful);
    //     tradeOrders.add(tradeOrder);

    //     return ResponseEntity.ok().build();
    // }

 

    // private boolean yourTradeConfirmationLogic(TradeOrder tradeOrder) {
    //     // Implement your trade confirmation logic here.
    //     // Return true if the trade was successful, otherwise false.
    // }

