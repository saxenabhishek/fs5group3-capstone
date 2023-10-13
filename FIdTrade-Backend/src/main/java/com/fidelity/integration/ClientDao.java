package com.fidelity.integration;

import java.math.BigDecimal;
import java.util.Set;

import com.fidelity.business.Client;
import com.fidelity.business.ClientIdentification;
import com.fidelity.business.Person;

public interface ClientDao {
	Client getClientsByID(String id);
	Client getClientsByEmail(String email);
	String getIdFromEmail(String email);
	Integer checkIfRowExists(String string);
	
	void insertPerson(Person person);
	void insertClientIdentification(ClientIdentification clientIdentification, String clientId);
	void insertBalance(String clientId, BigDecimal balance);
	int doesEmailAlreadyExist(String email);
	int doesClientIdentificationAlreadyExist(Set<ClientIdentification> clientIdentification);
}
