package com.fidelity.business;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.*;    
import java.util.*;  

public class Person {
	private long id;
    private String country;
    private String postalCode;
    private String dob;
    private String email;
    private String password;

    public Person(long id, String country, String postalCode, String dob, String email, String password) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cant be negative");
        }
        if (id == 0) {
            throw new IllegalArgumentException("ID cannot be 0");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        if (email == null ) {
            throw new IllegalArgumentException("email address cant be null");
        }
        if (password == null ) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (postalCode == null) {
            throw new IllegalArgumentException("postal code cannot be null");
        }
        if (dob == null ) {
            throw new IllegalArgumentException("dob cannot be null");
        }
        if (dob.isEmpty()) {
            throw new IllegalArgumentException("dob cannot be empty.");
        }
        if (postalCode.isEmpty()) {
            throw new IllegalArgumentException("postal code cannot be empty.");
        }
        if (country == null ) {
            throw new IllegalArgumentException("dob cannot be null");
        }
        if (country.isEmpty()) {
            throw new IllegalArgumentException("dob cannot be empty.");
        }

        this.id = id;
        this.country = country;
        this.postalCode = postalCode;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    public static boolean isValidEmail(String email) {

    	String EMAIL_REGEX = "^(?=.{1,256}$)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return id == person.id &&
                Objects.equals(country, person.country) &&
                Objects.equals(postalCode, person.postalCode) &&
                Objects.equals(dob, person.dob) &&
                Objects.equals(email, person.email) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, postalCode, dob, email, password);
    }
}