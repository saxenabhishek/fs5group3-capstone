package com.fidelity.integration;


import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Preference;


@Repository("preferencesDao")
public class PreferenceDaoMyBatisImpl {
	@Autowired
	private Logger logger;

	@Autowired
	private PreferenceMapper preferenceMapper;

	public List<Preference> getAllPreference() {
		logger.debug("enter");
		return preferenceMapper.getAllPreference();
	}

    @Transactional
	public boolean insertPreference(Preference pref) {
    	Objects.requireNonNull(pref);
    	if (pref.getInvestmentPurpose()=="") {
			throw new IllegalArgumentException("Purpose cannot be an empty string");
		}
		logger.debug("inserting preference " + pref);
		
		
		return preferenceMapper.insertPreference(pref) == 1;
	}
    @Transactional
	public boolean updatePreference(Preference pref) {
    	Objects.requireNonNull(pref);
    	if (pref.getInvestmentPurpose()=="") {
			throw new IllegalArgumentException("Purpose cannot be an empty string");
		}
		logger.debug("updating preference " + pref);
		
		return preferenceMapper.updatePreference(pref) == 1;
	}


	
}
