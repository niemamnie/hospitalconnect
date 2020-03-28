package de.hackathon.hospitalconnect.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternException extends RuntimeException {
    private String message;
    private HttpStatus responseHttpStatus;

    public InternException(String message, HttpStatus responseHttpStatus) {
        this.message = message;
        this.responseHttpStatus = responseHttpStatus;
    }
}
