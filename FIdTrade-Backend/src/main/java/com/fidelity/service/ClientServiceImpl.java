package com.fidelity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

import org.slf4j.Logger;
import com.fidelity.business.Client;
import com.fidelity.business.ClientFMTS;
import com.fidelity.business.ClientIdentification;
import com.fidelity.integration.ClientDaoImpl;
import com.fidelity.integration.FMTSDao;
import com.fidelity.business.Preference;


@Service
public class ClientServiceImpl implements ClientService{
	@Autowired
	private ClientDaoImpl clientDao;

	@Autowired
	private FMTSDao fmtsDao; 

	@Autowired
	Logger logger;

	@Override
	public ClientFMTS login(String email, String pswd) {
		Client client = clientDao.getClientsByEmail(email);
		ClientFMTS fmtsResponse= null;
		
		if(client != null) {
			if (client.getPerson().getPassword().equals(pswd))
				fmtsResponse = fmtsDao.verifyClientInformation(client);	
			
			return fmtsResponse;		
		}
		else 
			throw new FidTradeDatabaseException("Client does exist in db, register first");
	} 

	@Override
	public ClientFMTS register(Client client) {
		String enteredEmail = client.getPerson().getEmail();

		Set<ClientIdentification> clientIdentification = client.getIdentification();
		ClientFMTS fmtsResponse = null;
		if (verifyEmailAddress(enteredEmail) == 0 && 
				clientDao.doesClientIdentificationAlreadyExist(clientIdentification) == 0) {
			try {
				fmtsResponse = fmtsDao.verifyClientInformation(client);
                client.getPerson().setId(fmtsResponse.getClientId());
                clientDao.insertPerson(client.getPerson());
                for(ClientIdentification identification: clientIdentification) 
                	clientDao.insertClientIdentification(identification, fmtsResponse.getClientId());
                
                clientDao.insertBalance(client.getPerson().getId(), new BigDecimal(1000000.00));
			} catch (Exception e) {
				String msg = "Error while inserting Person, email Already exist";
				e.printStackTrace();
				throw new FidTradeDatabaseException(msg, e);
			}
			return fmtsResponse;

		}
		else {
			return null;
		}
	} 

	@Override
	public int verifyEmailAddress(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) 
			throw new IllegalArgumentException("Email is not in the correct format");

		return clientDao.doesEmailAlreadyExist(email);
	}

	@Override
	public List<Preference> findAllPreference() {
		List<Preference> preferences;		
		try {
			preferences = clientDao.queryForAllPreference();
		} 
		catch (Exception e) {
			String msg = "Error querying all Preference in the  database.";
			throw new PreferenceDatabaseException(msg, e);
		}		
		return preferences;
	}

	@Override
	public  Preference findPreferenceById(String id) {	
		try {
			Preference preference = clientDao.queryForPreferenceById(id);
			return preference;
		} 
		catch (Exception e) {
			String msg = String.format("Error querying For Preference with id in the database" + id);
			throw new PreferenceDatabaseException(msg, e);
		}	
	}

	@Override
	public int addPreference(Preference preference) {
		int count = 0;
		try {			
			count = clientDao.insertPreference(preference);		
		} 
		catch (DuplicateKeyException e) {
			throw e; 
		}
		catch (Exception e) {
			String msg = "Error inserting Preference into the  database.";
			throw new PreferenceDatabaseException(msg, e);
		}
		return count;
	}

	@Override
	public int modifyPreference(Preference preference) {
		validatePreference(preference);
		int count = 0;
		try {
			count = clientDao.updatePreference(preference);
		} 
		catch (Exception e) {
			String msg = "Error updating Preference in the  database.";
			throw new PreferenceDatabaseException(msg, e);
		}
		return count;
	}	

	private void validatePreference(Preference preference) {
		if (preference.getInvestmentPurpose() == null || preference.getInvestmentPurpose() == "" 
			|| preference.getRiskTolerance() == null || preference.getIncomeCategory() == null 
				|| preference.getLengthOfInvestment() == null) 
			throw new IllegalArgumentException("Preference is not fully populated: " + preference);
	}
}
