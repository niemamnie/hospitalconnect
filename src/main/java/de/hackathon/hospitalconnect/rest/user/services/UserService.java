package de.hackathon.hospitalconnect.rest.user.services;

import de.hackathon.hospitalconnect.objects.hospitals.User;
import de.hackathon.hospitalconnect.objects.hospitals.repositories.HospitalLocationRepository;
import de.hackathon.hospitalconnect.objects.hospitals.repositories.UserRepository;
import de.hackathon.hospitalconnect.rest.exceptions.InternException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final HospitalLocationRepository locationRepository;

    public UserService(UserRepository userRepository, HospitalLocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    public Long saveNewHospital(User user) {
        User saved = userRepository.saveAndFlush(user);
        return saved.getId();
    }

    public User getUser(Long id) {
        try {
            Optional<User> anyHospital = userRepository.findById(id);
            if (anyHospital.isPresent()) {
                return anyHospital.get();
            }
        } catch (EntityNotFoundException e) {
            throw new InternException("Could not Found", HttpStatus.NOT_FOUND);
        }
        throw new InternException("Could not Found", HttpStatus.NOT_FOUND);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
