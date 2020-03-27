package de.hackathon.hospitalconnect.rest.address;

import de.hackathon.hospitalconnect.model.user.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/get/address/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        return new ResponseEntity<>(addressService.getAddress(id), HttpStatus.OK);
    }

    @PatchMapping("/patch/address/{id}")
    public ResponseEntity patchAddress(@PathVariable Long id, @RequestBody Address address) {
        addressService.patchAddress(id, address);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
