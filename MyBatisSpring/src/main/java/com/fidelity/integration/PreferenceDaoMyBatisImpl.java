package com.fidelity.integration;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


	public boolean insertPreference(Preference pref) {
		logger.debug("inserting preference " + pref);
		
		return preferenceMapper.insertPreference(pref) == 1;
	}

	public boolean updatePreference(Preference pref) {
		logger.debug("updating preference " + pref);
		
		return preferenceMapper.updatePreference(pref) == 1;
	}


	
}
