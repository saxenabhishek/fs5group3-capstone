package Classes ;

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

	public Object getPerson() {
		
		return person;
	}

	public Object getIdentification() {
		
		return identification;
	}

    // Getter and setter methods for person and identification
}

