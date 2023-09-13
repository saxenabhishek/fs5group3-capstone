package models;

import java.util.Objects;

public class ClientIdentification {
    private String type;
    private String value;

    public ClientIdentification(String type, String value) {
        if (type == null) {
            throw new IllegalArgumentException("Identification type cannot be null.");
        }
        if (type.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException("Identification type and value cannot be empty.");
        }

        this.type = type;
        this.value = value;
    }

    // Getter and setter methods for type and value

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ClientIdentification that = (ClientIdentification) obj;
        return Objects.equals(type, that.type) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}