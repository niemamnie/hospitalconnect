package de.hackathon.hospitalconnect.rest.contact;


import de.hackathon.hospitalconnect.model.user.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/get/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        return new ResponseEntity<>(contactService.getContact(id), HttpStatus.OK);
    }

    @PatchMapping("/patch/contact/{id}")
    public ResponseEntity patchContact(@PathVariable Long id, @RequestBody Contact contact) {
        contactService.updateContact(id, contact);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
