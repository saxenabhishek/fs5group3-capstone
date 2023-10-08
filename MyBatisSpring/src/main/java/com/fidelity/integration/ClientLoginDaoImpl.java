package com.fidelity.integration;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ClientLoginDao")
public class ClientLoginDaoImpl {
	
	@Autowired
	private Logger logger;

	@Autowired
	private ClientMapper ClientMapper;
	
	public String getEmail(String email) {
		if (email == null || email=="")
			throw new NullPointerException("Email Id can not be empty or null");
		
		if (ClientMapper.getEmailExistence(email) != null)
			return ClientMapper.getEmailExistence(email);		
		else 
			throw new NullPointerException("This email id does not exist");
	}
	
	public String getPassword(String email, String password) {
		if (password == null || password == "")
			throw new NullPointerException("Password can not be empty or null");
		
		if (ClientMapper.getPasswordExistence(email, password) != null)
			return ClientMapper.getPasswordExistence(email, password);		
		else 
			throw new NullPointerException("Password is incorrect");
	}
}