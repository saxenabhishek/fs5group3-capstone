package com.fidelity.integration;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.business.Client;
import com.fidelity.business.ClientIdentification;
import com.fidelity.business.Person;
import com.fidelity.mapper.ClientRegistrationMapper;

@Repository("clientRegDao")
public class ClientRegistrationDaoMyBatisImpl {
	@Autowired 
	private Logger logger;

	@Autowired
	private ClientRegistrationMapper clientRegMapper;
	
	public boolean insertPerson(Person person) {
		logger.debug("inserting person " + person);
		
		return clientRegMapper.insertPerson(person) == 1;
	}
	public boolean insertClient(Client client) {
		logger.debug("inserting client " + client);
		
		return clientRegMapper.insertClient(client.getPerson().getId(), client.getIdentification().getId()) == 1;
	}
	public boolean insertClientIdentification(ClientIdentification clientid) {
		logger.debug("inserting client with identification " + clientid);
		
		return clientRegMapper.insertClientIdentification(clientid) == 1;
	}
}


 

	

 



 

