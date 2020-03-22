package de.hackathon.hospitalconnect.rest.user;

import de.hackathon.hospitalconnect.objects.user.User;
import de.hackathon.hospitalconnect.objects.user.repositories.AddressRepository;
import de.hackathon.hospitalconnect.objects.user.repositories.UserRepository;
import de.hackathon.hospitalconnect.rest.exceptions.InternException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class UserController {

    private final UserRepository userRepository;
    private final AddressRepository locationRepository;

    public UserController(UserRepository userRepository, AddressRepository locationRepository) {
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
        List<User> users = userRepository.findAll();
        for (User userItem : users) {
            userItem.getPersonal_resources().size();
            userItem.getMaterial_resources().size();
        }
        return users;
    }
}
