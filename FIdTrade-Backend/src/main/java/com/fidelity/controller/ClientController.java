package com.fidelity.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.service.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.server.ServerWebInputException;
import com.fidelity.business.*;
import com.fidelity.integration.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private static final String DB_ERROR_MSG = 
			"Error communicating with the  database";

    @Autowired
	private ClientService service;

    
    @PostMapping("/register")
    ResponseEntity<Integer> registerNewClient(){
        throw new java.lang.UnsupportedOperationException();
    }

    //Preference
    @GetMapping("/preference/{id}")
	public Preference queryForPreferenceById(@PathVariable int id) {
		Preference preference = null;
		try {
			preference = service.findPreferenceById(id);
		} 
		catch (Exception e) {
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		
		if (Preference == null) {
			throw new ServerWebInputException(
					"No Preference in the warehouse with id = " + id);
		}
		return Preference;
	}

    @PostMapping("/preference/add")
	@ResponseStatus(HttpStatus.CREATED)  
	public DatabaseRequestResult insertPreference(@RequestBody Preference preference) {
		int count = 0;
		try {
			count = service.addPreference(preference);
		} 
		catch (DuplicateKeyException e) {
			// If the Preference id is already present in the database, return status 400
			throw new ServerWebInputException("client id is a duplicate: " + preference);
		}
		catch (Exception e) {
			// For any other service error, return status 500
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		if (count == 0) {
			throw new ServerWebInputException("Can't insert Preference " + preference);
		}
		return new DatabaseRequestResult(count);
	}

    @PutMapping("/preference/update")
	@ResponseStatus(HttpStatus.ACCEPTED)  
	public DatabaseRequestResult updatePreference(@RequestBody Preference preference) {
		int count = 0;
		try {
			count = service.modifyPreference(preference);
		} 
		catch (Exception e) {
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		if (count == 0) {
			throw new ServerWebInputException("Can't update Preference " + preference);
		}
		return new DatabaseRequestResult(count);
	}



}

