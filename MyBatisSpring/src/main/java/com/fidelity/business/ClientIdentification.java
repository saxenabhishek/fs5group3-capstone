package com.fidelity.business;

import java.util.Objects;

public class ClientIdentification {
	
	private long id;
	private String person_id;
    private String type;
    private String value;

    public ClientIdentification(long id,String person_id,String type, String value) {
        if (type == null) {
            throw new IllegalArgumentException("Identification type cannot be null.");
        }
        if (type.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException("Identification type and value cannot be empty.");
        }

        this.setType(type);
        this.setValue(value);
        this.setPerson_id(person_id);
        this.setId(id);
    }


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, person_id, type, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientIdentification other = (ClientIdentification) obj;
		return id == other.id && Objects.equals(person_id, other.person_id) && Objects.equals(type, other.type)
				&& Objects.equals(value, other.value);
	}

}