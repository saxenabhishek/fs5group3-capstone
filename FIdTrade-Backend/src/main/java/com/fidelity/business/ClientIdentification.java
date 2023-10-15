package com.fidelity.business;

import java.util.Objects;

public class ClientIdentification {
    private String type, value;

    public ClientIdentification() { }
    
    public ClientIdentification(String type, String value) {
        if (type == null) {
            throw new IllegalArgumentException("Identification type cannot be null.");
        }
        if (type.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException("Identification type and value cannot be empty.");
        }

        this.setType(type);
        this.setValue(value);
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
		return Objects.hash(type, value);
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
		return Objects.equals(type, other.type)
				&& Objects.equals(value, other.value);
	}
}