package com.duasatuwo.api.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthentificationKey {
    
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        // this.password = password;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password).toString();
    }
}
