package com.fidelity.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fidelity.business.Client;
import com.fidelity.business.ClientFMTS;
import com.fidelity.service.ClientService;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
	
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
}
