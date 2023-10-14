package com.fidelity.service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelity.business.Client;
import com.fidelity.business.ClientFMTS;
import com.fidelity.business.ClientIdentification;
import com.fidelity.integration.ClientDaoImpl;
import com.fidelity.integration.FMTSDao;

/*
 * package com.fidelity.services; import java.util.Collections; import
 * java.util.HashSet; import java.util.Set;
 * 
 * import com.fidelity.business.Preference;
 * 
 * public class ClientService { private Set<Preference> preferences ;
 * 
 * 
 * public ClientService() {
 * 
 * this.preferences = new HashSet<>();
 * 
 * 
 * } public void addPreference(Preference pre) { if(pre==null) { throw new
 * NullPointerException("Preference can't be null "); } if(pre.isAccept()) {
 * preferences.add(pre); }
 * 
 * }
 * 
 * public void updatePreference(String purpose,String risk,String
 * category,String length) { if (purpose==null || risk==null || category==null
 * || length==null) { throw new NullPointerException("All fields are required");
 * } for (Preference pre:preferences) { pre.setAccept(true);
 * if(!pre.getInvestmentPurpose().equals(purpose)) { if(purpose.length()>1) {
 * pre.setInvestmentPurpose(purpose); }
 * 
 * }
 * 
 * if(!pre.getRiskTolerance().equals(risk)) { if(risk.length()>1) {
 * pre.setRiskTolerance(risk); }
 * 
 * } if(!pre.getIncomeCategory().equals(category)) { if(category.length()>1) {
 * pre.setIncomeCategory(category); }
 * 
 * } if(!pre.getLengthOfInvestmet().equals(length)) { if(length.length()>1) {
 * pre.setLengthOfInvestmet(length); }
 * 
 * }
 * 
 * } }
 * 
 * public Set<Preference> getPreferences() { return
 * Collections.unmodifiableSet(preferences); }
 * 
 * }
 */

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
}
