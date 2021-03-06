package de.hackathon.hospitalconnect.rest.address;


import de.hackathon.hospitalconnect.exceptions.InternException;
import de.hackathon.hospitalconnect.model.user.Address;
import de.hackathon.hospitalconnect.model.user.repositories.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddress(Long id) {
        Optional<Address> address = addressRepository.getById(id);
        if (address.isPresent()) {
            return address.get();
        } else {
            throw new InternException("Unable to find address in database", HttpStatus.NOT_FOUND);
        }

    }

    public void patchAddress(Long id, Address address) {
        try {
            if (!id.equals(address.getId())) {
                address.setId(id);
            }
            Optional<Address> anyAddress = addressRepository.getById(id);
            if (anyAddress.isPresent()) {
                address.setUser(anyAddress.get().getUser());
            }
            addressRepository.saveAndFlush(address);
        } catch (RuntimeException exception) {
            throw new InternException("Unable to find address in database", HttpStatus.NOT_FOUND);
        }
    }
}
