package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.*;

public interface PreferenceMapper {
	List<Preference> getAllPreference();
    int insertPreference(Preference preference);
    int updatePreference(Preference preference);
	

}