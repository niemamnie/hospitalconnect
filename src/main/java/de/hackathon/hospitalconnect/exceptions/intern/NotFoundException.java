package de.hackathon.hospitalconnect.exceptions.intern;

import de.hackathon.hospitalconnect.exceptions.InternException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends InternException {
    public NotFoundException() {
        super("We could not find, what you are looking for", HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message + "could not be found", HttpStatus.NOT_FOUND);
    }
}
