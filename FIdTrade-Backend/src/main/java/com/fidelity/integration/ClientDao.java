package com.fidelity.integration;

import java.util.List;
import java.math.BigDecimal;
import java.util.Set;
import com.fidelity.business.Client;
import com.fidelity.business.ClientIdentification;
import com.fidelity.business.Person;
import com.fidelity.business.Preference;

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
	
	List<Preference> queryForAllPreference();
    Preference queryForPreferenceById(String id);
    int insertPreference(Preference preference);
    int updatePreference(Preference preference);
}
