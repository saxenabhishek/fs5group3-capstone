package com.fidelity.integration;

import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fidelity.business.Trade;

@Repository("ClientLoginDao")
public class ClientLoginDaoImpl {
	private final Logger logger = LoggerFactory.getLogger(getClass());

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