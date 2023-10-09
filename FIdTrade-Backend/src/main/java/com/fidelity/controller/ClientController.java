package com.fidelity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fidelity.business.Order;
import com.fidelity.integration.ReportActivityDao;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ReportActivityDao dao;
    
    @PostMapping("/register")
    ResponseEntity<Integer> registerNewClient(){
        throw new java.lang.UnsupportedOperationException();
    }  
    @GetMapping(value="/ping")
	public String ping() {
		return "Hello";
	}
    @GetMapping("/activityReport")
    public ResponseEntity<List<Order>> getActivityReport() {
        // Call a service method to retrieve the list of orders
        List<Order> orders = dao.getReportActivity();

        // Check if orders are empty or null
        if (orders == null || orders.isEmpty()) {
            // If there are no orders, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            // If orders are found, return a 200 OK response with the list of orders
            return ResponseEntity.ok(orders);
        }
    }

}

