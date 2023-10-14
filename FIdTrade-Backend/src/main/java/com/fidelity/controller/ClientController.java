package com.fidelity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.server.ServerWebInputException;
import com.fidelity.business.*;

import com.fidelity.service.ClientService;
import com.fidelity.business.Client;
import com.fidelity.business.ClientFMTS;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
	private static final String DB_ERROR_MSG = 
			"Error communicating with the  database";

	@Autowired
	Logger logger;

	@Autowired
	ClientService clientService;

	@PostMapping("/register")
	 ResponseEntity<ClientDTO> registerClient(@RequestBody Client client) throws SQLException{
		ResponseEntity<ClientDTO> response = null;
		ClientFMTS clientFmts = null;
		System.out.println(client.getPerson().getEmail());
		
		try {
			clientFmts = clientService.register(client);
			if(clientFmts == null) 
				response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			else 
				response = ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(clientFmts.getClientId(), clientFmts.getToken()));
		}
		catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return response;	
	}

	
	@PostMapping("/login")
	ResponseEntity<ClientDTO> clientLogin(@RequestBody LoginRequest loginRequest) throws SQLException{
		String email = loginRequest.getEmail();
		String pswd = loginRequest.getPassword();
		ClientFMTS clientFmts= null;
		ResponseEntity<ClientDTO> response = null;
		
		try {
			clientFmts = clientService.login(email, pswd);
			System.out.println (email + ", " + pswd);

			if(clientFmts == null) 
				response =ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			else
				response = ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(clientFmts.getClientId(), clientFmts.getToken()));
		}
		catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}
	
	@GetMapping("/verify-email/{email}")
	ResponseEntity<Integer> verifyEmailAddress(@PathVariable String email) throws SQLException{
		Integer verification;
		ResponseEntity<Integer> response = null;
		
		try {
			verification = clientService.verifyEmailAddress(email);
			if(verification.intValue() == 1 || verification.intValue() == 0)
				response = ResponseEntity.status(HttpStatus.OK).body(verification);
			else
				response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

    @GetMapping("/preference/{id}")
	public ResponseEntity<Preference> queryForPreferenceById(@PathVariable String id) {
		 
		try {
			Preference preference = clientService.findPreferenceById(id);
			ResponseEntity<Preference> result;
			if (preference != null ) {
				result = ResponseEntity.ok(preference); 
			}
			else {
				
				result = ResponseEntity.noContent().build();
			}
			return result;
		} 
		catch (RuntimeException e) {
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
    }
	

    @PostMapping("/preference/add")
	@ResponseStatus(HttpStatus.CREATED)  
	public DatabaseRequestResult insertPreference(@RequestBody Preference preference) {
		int count = 0;
		try {			
			count = clientService.addPreference(preference);			
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
			count = clientService.modifyPreference(preference);
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
