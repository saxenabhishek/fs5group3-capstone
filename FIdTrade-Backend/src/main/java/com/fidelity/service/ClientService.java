package com.fidelity.service;

import java.util.List;

import com.fidelity.business.Preference;
import com.fidelity.business.Client;
import com.fidelity.business.ClientFMTS;

public interface ClientService {
	ClientFMTS login(String email, String pswd);
	ClientFMTS register(Client client);
	int verifyEmailAddress(String email);

    List<Preference> findAllPreference();
    Preference findPreferenceById(String id);
    int addPreference(Preference preference);
    int modifyPreference(Preference preference);
}
