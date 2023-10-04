package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.*;

public interface ClientDao {
    //Preference
	List<Preference> queryForAllPreference();
    Preference queryForPreferenceById(String id);
    int insertPreference(Preference preference);
    int updatePreference(Preference preference);
	

}