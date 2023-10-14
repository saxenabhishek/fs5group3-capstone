package com.fidelity.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.*;
import com.fidelity.integration.*;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientDao dao;
    
    //Preference
	@Override
	public List<Preference> findAllPreference() {
		List<Preference> preferences;
		
		try {
			preferences = dao.queryForAllPreference();
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
			Preference preference = dao.queryForPreferenceById(id);
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
			
			count = dao.insertPreference(preference);
			
			
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
			count = dao.updatePreference(preference);
		} 
		catch (Exception e) {
			String msg = "Error updating Preference in the  database.";
			throw new PreferenceDatabaseException(msg, e);
		}

		return count;
	}

	

	private void validatePreference(Preference preference) {
		if (preference.getInvestmentPurpose() == null || preference.getInvestmentPurpose()=="" || preference.getRiskTolerance()==null || preference.getIncomeCategory()==null|| preference.getLengthOfInvestment()==null 
			 ) {
				throw new IllegalArgumentException("Preference is not fully populated: " + preference);
		}
	}

}
	

	