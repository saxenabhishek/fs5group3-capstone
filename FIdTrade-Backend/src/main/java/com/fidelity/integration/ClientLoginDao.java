package com.fidelity.integration;

public interface ClientLoginDao {
	String getEmail(String email);
	String getPassword(String email, String password);
}
