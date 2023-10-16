package com.fidelity.controller.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletDTO {
	String clientId;
	BigDecimal walletBalance;	
	
	public WalletDTO() { }
	
	public WalletDTO(String clientId, BigDecimal walletBalance) {
		super();
		this.clientId = clientId;
		this.walletBalance = walletBalance;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public BigDecimal getWalletBalance() {
		return walletBalance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, walletBalance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WalletDTO other = (WalletDTO) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(walletBalance, other.walletBalance);
	}

	@Override
	public String toString() {
		return "WalletDTO [clientId=" + clientId + ", walletBalance=" + walletBalance + "]";
	}	
}
