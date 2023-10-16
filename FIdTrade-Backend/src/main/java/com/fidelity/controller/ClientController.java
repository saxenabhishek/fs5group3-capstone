package com.fidelity.controller;

import java.util.List;

import java.sql.SQLException;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.fidelity.service.ClientService;
import com.fidelity.business.Client;
import com.fidelity.business.ClientFMTS;

import com.fidelity.business.Order;
import com.fidelity.controller.dto.ClientDTO;
import com.fidelity.controller.dto.DatabaseRequestResult;
import com.fidelity.controller.dto.LoginRequest;
import com.fidelity.integration.ReportActivityDao;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
	@Autowired
	private ReportActivityDao activityDao;

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
	public ResponseEntity<DatabaseRequestResult> verifyEmailAddress(@PathVariable String email) throws SQLException{
		int verification= 0;
		ResponseEntity<DatabaseRequestResult> response = null;
		
		try {
			verification = clientService.verifyEmailAddress(email);
			if(verification == 1 || verification == 0)
				response = ResponseEntity.status(HttpStatus.OK).body(new DatabaseRequestResult(verification));
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
	public ResponseEntity<DatabaseRequestResult> insertPreference(@RequestBody Preference preference) {
		int count = 0;		
		ResponseEntity<DatabaseRequestResult> response = null;
		System.out.println(preference);
		try {			
			count = clientService.addPreference(preference);
			if(count == 1)
				response = ResponseEntity.status(HttpStatus.OK).body(new DatabaseRequestResult(count));
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
		return response;
	}

    @PutMapping("/preference/update")
	@ResponseStatus(HttpStatus.ACCEPTED)  
	public ResponseEntity<DatabaseRequestResult> updatePreference(@RequestBody Preference preference) {
		int count = 0;
		ResponseEntity<DatabaseRequestResult> response = null;

		try {
			count = clientService.modifyPreference(preference);
			if(count == 1)
				response = ResponseEntity.status(HttpStatus.OK).body(new DatabaseRequestResult(count));
		} 
		catch (Exception e) {
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		if (count == 0) {
			throw new ServerWebInputException("Can't update Preference " + preference);
		}
		return response;
	}
	
    @GetMapping("/activityReport")
    public ResponseEntity<List<Order>> getActivityReport() {
        // Call a service method to retrieve the list of orders
        List<Order> orders = activityDao.getReportActivity();

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
