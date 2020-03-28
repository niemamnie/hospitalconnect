package de.hackathon.hospitalconnect.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ThirdPartyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpected(Exception exception, WebRequest webRequest) {
        exception.printStackTrace();
        return handleExceptionInternal(
                exception,
                "Unexpected issue occurred",
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest);
    }
}
