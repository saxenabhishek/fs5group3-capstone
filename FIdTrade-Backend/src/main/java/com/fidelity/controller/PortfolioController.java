package com.fidelity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    
    @PostMapping("/register")
    ResponseEntity<Integer> registerNewClient(){
        throw new java.lang.UnsupportedOperationException();
    }
}
