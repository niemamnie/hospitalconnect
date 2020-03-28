package de.hackathon.hospitalconnect.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptionService {

    private final PasswordEncoder passwordEncoder;

    public PasswordEncryptionService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public String encrypt(String pass) {
        return passwordEncoder.encode(pass);
    }

    public boolean match(String rowPass, String encryptedPass) {
        return passwordEncoder.matches(rowPass, encryptedPass);
    }
}
