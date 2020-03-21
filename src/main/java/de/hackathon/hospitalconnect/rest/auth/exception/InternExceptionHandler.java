package de.hackathon.hospitalconnect.rest.auth.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InternExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InternException.class)
    public ResponseEntity<Object> handleInternException(InternException exception, WebRequest webRequest) {
        return handleExceptionInternal(
                exception,
                exception.getMessage(),
                new HttpHeaders(),
                exception.getResponseHttpStatus(),
                webRequest);
    }
}
