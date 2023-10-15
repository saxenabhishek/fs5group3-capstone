package com.fidelity.controller;

import java.util.Objects;

public class ClientDTO {
	String clientId;
	String token;
	
	public ClientDTO() { }
	
	public ClientDTO(String clientId, String token) {
		super();
		this.clientId = clientId;
		this.token = token;
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDTO other = (ClientDTO) obj;
		return clientId == other.clientId && Objects.equals(token, other.token);
	}

	@Override
	public String toString() {
		return "ClientDTO [clientId=" + clientId + ", token=" + token + "]";
	}	
}
