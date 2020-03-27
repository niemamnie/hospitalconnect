package de.hackathon.hospitalconnect.model;

import de.hackathon.hospitalconnect.model.user.Address;
import de.hackathon.hospitalconnect.model.user.Coordinates;
import de.hackathon.hospitalconnect.model.user.User;
import de.hackathon.hospitalconnect.model.user.repositories.AddressRepository;
import de.hackathon.hospitalconnect.model.user.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class repositoriesTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository hospitalLocationRepository;


    @Test
    public void jpaTest() {
        User user = new User();
        Address location = new Address();
        Coordinates coordinates = new Coordinates();
        coordinates.setLat(12.22);
        coordinates.setLng(14.22);

        location.setCoordinates(coordinates);

        user.setName("test hospital");
        user.setAddress(location);
        hospitalLocationRepository.save(location);
        User savedUser = userRepository.saveAndFlush(user);

        Assertions.assertEquals(user, savedUser);
        //Assertions.assertTrue(hospitalRepository.existsById(savedHospital.getId()));
    }
}
