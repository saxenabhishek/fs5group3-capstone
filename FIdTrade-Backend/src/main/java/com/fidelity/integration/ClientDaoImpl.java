package com.fidelity.integration;


import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Preference;
import om.fidelity.mapper.ClientMapper;


@Repository("clientDao")
public class ClientDaoImpl implements ClientDao {
	@Autowired
	private Logger logger;

	@Autowired
	private ClientMapper mapper;
    

    @Override
	public List<Preference> getAllPreference() {
		logger.debug("enter");
		return mapper.getAllPreference();
	}
	
    @Override
	public Preference getPreferenceById() {
		logger.debug("enter");
		return mapper.getPreferenceById();
	}

    @Transactional
    @Override
	public int insertPreference(Preference pref) {
		logger.debug("inserting preference " + pref);
		int count=mapper.insertPreference(pref);
		
		return count ;
	}
    @Transactional
    @Override
	public int updatePreference(Preference pref) {
		logger.debug("updating preference " + pref);
		int count=mapper.updatePreference(pref);
		return count;
	}


	
}