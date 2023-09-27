package com.fidelity.integration;

import com.fidelity.business.Client;
import com.fidelity.business.ClientIdentification;
import com.fidelity.business.Person;

public interface ClientRegistrationMapper {
	int insertPerson(Person person);
	int insertClientIdentification(ClientIdentification clientid);
	int insertClient(String clientId, long identificationId);
	
}
