package com.fidelity.service;

import java.util.List;

import com.fidelity.business.Preference;


public interface ClientService {
    //Preference
	List<Preference> findAllPreference();
    Preference findPreferenceById(String id);
    int addPreference(Preference preference);
    int modifyPreference(Preference preference);
}
