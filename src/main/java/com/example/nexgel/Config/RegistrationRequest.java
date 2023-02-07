package com.example.nexgel.Config;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String branch;
    private final String password;
    
    public RegistrationRequest(String firstname, String lastname, String email, String branch, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.branch = branch;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public String getBranch() {
        return branch;
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
