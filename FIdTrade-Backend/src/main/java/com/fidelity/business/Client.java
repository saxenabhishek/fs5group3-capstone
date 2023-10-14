package com.fidelity.business;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class Client{
    private Person person;
    private Set<ClientIdentification> identification;
    private BigDecimal walletBalance;

    public Client() { }
    public Client(Person person, Set<ClientIdentification> identification, BigDecimal walletBal) {
        this.person = person;
        this.identification = identification;
        if (person == null || identification == null) {
            throw new IllegalArgumentException("Person and identification cannot be null.");
        }
        this.walletBalance= walletBal;
    }

	public Person getPerson() {		
		return person;
	}

	public BigDecimal getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(BigDecimal walletBalance) {
		this.walletBalance = walletBalance;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Set<ClientIdentification> getIdentification() {
		return identification;
	}

	public void setIdentification(Set<ClientIdentification> identification) {
		this.identification = identification;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identification, person);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(identification, other.identification) && Objects.equals(person, other.person)
				&& Objects.equals(walletBalance, other.walletBalance);
	}

	@Override
	public String toString() {
		return "Client [person=" + person + ", identification=" + identification + ", walletBalance=" + walletBalance
				+ "]";
	}
}

