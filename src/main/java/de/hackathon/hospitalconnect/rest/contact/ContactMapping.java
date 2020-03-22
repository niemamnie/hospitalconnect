package de.hackathon.hospitalconnect.rest.contact;


import de.hackathon.hospitalconnect.objects.user.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping
public class ContactMapping {

    private final ContactController contactController;

    public ContactMapping(ContactController contactController) {
        this.contactController = contactController;
    }

    @GetMapping("/get/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        return new ResponseEntity<>(contactController.getContact(id), HttpStatus.OK);
    }

    @PatchMapping("/patch/contact/{id}")
    public ResponseEntity patchContact(@PathVariable Long id, @RequestBody Contact contact) {
        contactController.updateContact(id, contact);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
