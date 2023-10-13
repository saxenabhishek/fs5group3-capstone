package com.fidelity.service;

import com.fidelity.business.Client;
import com.fidelity.business.ClientFMTS;

public interface ClientService {
	ClientFMTS login(String email, String pswd);
	ClientFMTS register(Client client);
	int verifyEmailAddress(String email);
}
