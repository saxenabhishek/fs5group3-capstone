package com.fidelity.business;

public class Client {
    private Person person;
    private ClientIdentification identification;

    public Client(Person person, ClientIdentification identification) {
        this.person = person;
        this.identification = identification;
        if (person == null || identification == null) {
            throw new IllegalArgumentException("Person and identification cannot be null.");
        }

    }

	public Person getPerson() {
		
		return person;
	}

	public ClientIdentification getIdentification() {
		
		return identification;
	}

}

