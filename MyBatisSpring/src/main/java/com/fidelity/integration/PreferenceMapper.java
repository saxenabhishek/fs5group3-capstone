package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.*;

public interface PreferenceMapper {
	List<Preference> getAllPreference();
    void insertPreference(Preference preference)();
    void updatePreference(Preference preference)();
	

}