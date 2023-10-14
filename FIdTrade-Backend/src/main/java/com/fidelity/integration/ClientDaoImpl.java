package com.fidelity.integration;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.business.Client;
import com.fidelity.business.ClientIdentification;
import com.fidelity.business.Person;
import com.fidelity.integration.mapper.ClientMapper;
import com.fidelity.service.FidTradeDatabaseException;

@Repository("clientDao")
public class ClientDaoImpl implements ClientDao {
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Override
	public Client getClientsByID(String id) {
		if (id == null) 
		    throw new FidTradeDatabaseException("Client can't be null: " + id);
		
		Client client= clientMapper.getClientsByID(id);
		if (client == null) 
	        throw new FidTradeDatabaseException("Client not found for ID: " + id);
		
		return client;
	} 

	@Override
	public Client getClientsByEmail(String email) {
		if (email==null) 
		    throw new FidTradeDatabaseException("Client email can't be null: " + email);
		
		return clientMapper.getClientsByEmail(email);

	}

	@Override
	public String getIdFromEmail(String email) {
		return clientMapper.getIdFromEmail(email);
	}

	@Override
	public Integer checkIfRowExists(String string) {
		return clientMapper.checkIfRowExists(string);
	}

	@Override
	public void insertPerson(Person person) {
		clientMapper.insertPerson(person);
	} 

	@Override
	public void insertClientIdentification(ClientIdentification clientIdentification, String clientId) {
		clientMapper.insertClientIdentification(clientIdentification, clientId);
	} 
	
	@Override
	public void insertBalance(String clientId, BigDecimal balance) {
		clientMapper.insertBalance(clientId, balance);
	} 

	@Override
	public int doesEmailAlreadyExist(String email) {
		return clientMapper.doesEmailAlreadyExist(email);
	} 

	@Override
	 public int doesClientIdentificationAlreadyExist(Set<ClientIdentification> clientIdentification) {
		int rowUpdate = 0;

		for(ClientIdentification identification: clientIdentification) {
				rowUpdate= clientMapper.doesClientIdentificationAlreadyExist(identification);
				if(rowUpdate == 1) 
					break;
		}
		return rowUpdate;
	}
}
