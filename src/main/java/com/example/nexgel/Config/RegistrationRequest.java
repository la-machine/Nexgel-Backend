package com.example.nexgel.Config;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String phonenumber;
    private final String password;
    
    public RegistrationRequest(String firstname, String lastname, String email, String phonenumber, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public String getEmail() {
        return email;
    }
    public String getLastname() {
        return lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    
}
