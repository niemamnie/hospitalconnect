package de.hackathon.hospitalconnect.rest.address;

import de.hackathon.hospitalconnect.objects.user.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping
public class AddressMapping {

    private final AddressController addressController;

    public AddressMapping(AddressController addressController) {
        this.addressController = addressController;
    }

    @GetMapping("/get/address/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        return new ResponseEntity<>(addressController.getAddress(id), HttpStatus.OK);
    }

    @PatchMapping("/patch/address/{id}")
    public ResponseEntity patchAddress(@PathVariable Long id, @RequestBody Address address) {
        addressController.patchAddress(id, address);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
