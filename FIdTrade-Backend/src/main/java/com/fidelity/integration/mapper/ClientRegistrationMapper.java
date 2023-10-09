package com.fidelity.integration.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fidelity.business.Client;
import com.fidelity.business.ClientIdentification;
import com.fidelity.business.Person;

@Mapper
public interface ClientRegistrationMapper {
	int insertPerson(Person person);
	int insertClientIdentification(ClientIdentification clientid);
	int insertClient(String clientId, long identificationId);
	
}
