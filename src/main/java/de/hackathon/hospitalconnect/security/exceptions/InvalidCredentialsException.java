package de.hackathon.hospitalconnect.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidCredentialsException() {
        super("Invalid Credentials");
    }
}
