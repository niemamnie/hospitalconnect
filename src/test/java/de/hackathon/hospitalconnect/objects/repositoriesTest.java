package de.hackathon.hospitalconnect.objects;

import de.hackathon.hospitalconnect.objects.hospitals.HospitalLocation;
import de.hackathon.hospitalconnect.objects.hospitals.User;
import de.hackathon.hospitalconnect.objects.hospitals.repositories.HospitalLocationRepository;
import de.hackathon.hospitalconnect.objects.hospitals.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class repositoriesTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HospitalLocationRepository hospitalLocationRepository;


    @Test
    public void jpaTest() {
        User user = new User();
        HospitalLocation location = new HospitalLocation();
        location.setLat(12.22);
        location.setLng(14.22);

        user.setName("test hospital");
        user.setLocation(location);
        hospitalLocationRepository.save(location);
        User savedUser = userRepository.saveAndFlush(user);

        Assertions.assertEquals(user, savedUser);
        //Assertions.assertTrue(hospitalRepository.existsById(savedHospital.getId()));
    }
}
