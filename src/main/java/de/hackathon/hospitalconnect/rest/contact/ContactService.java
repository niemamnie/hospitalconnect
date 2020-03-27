package de.hackathon.hospitalconnect.rest.contact;

import de.hackathon.hospitalconnect.model.user.Contact;
import de.hackathon.hospitalconnect.model.user.repositories.ContactRepository;
import de.hackathon.hospitalconnect.rest.exceptions.InternException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContactService {

    private final ContactRepository contactRepository;


    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public Contact getContact(Long id) {

        Optional<Contact> anyContact = contactRepository.getById(id);
        if (anyContact.isPresent()) {
            return anyContact.get();
        } else {
            throw new InternException("Unable to find contact in database", HttpStatus.NOT_FOUND);
        }
    }


    public void updateContact(Long id, Contact contact) {
        try {
            if (!contact.getId().equals(id)) {
                contact.setId(id);
            }
            Optional<Contact> anyContact = contactRepository.getById(id);
            if (anyContact.isPresent()) {
                contact.setUser(anyContact.get().getUser());
            }
            contactRepository.saveAndFlush(contact);
        } catch (RuntimeException exception) {
            throw new InternException("Unable to find contact in database", HttpStatus.NOT_FOUND);
        }
    }
}
