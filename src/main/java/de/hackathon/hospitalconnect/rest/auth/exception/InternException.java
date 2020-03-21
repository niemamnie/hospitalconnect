package de.hackathon.hospitalconnect.rest.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class InternException extends RuntimeException {
    private String message;
    private HttpStatus responseHttpStatus;

}
