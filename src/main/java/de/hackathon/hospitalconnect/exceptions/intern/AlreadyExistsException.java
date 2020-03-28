package de.hackathon.hospitalconnect.exceptions.intern;

import de.hackathon.hospitalconnect.exceptions.InternException;
import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends InternException {


    public AlreadyExistsException(String messages) {
        super(messages + "already exists", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
