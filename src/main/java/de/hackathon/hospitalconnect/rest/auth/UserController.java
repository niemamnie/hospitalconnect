package de.hackathon.hospitalconnect.rest.auth;

import de.hackathon.hospitalconnect.objects.user.User;
import de.hackathon.hospitalconnect.objects.user.repositories.UserRepository;
import de.hackathon.hospitalconnect.rest.auth.exception.InternException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/api/user")
public class UserController {
    final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping
    public ResponseEntity registerUser(@RequestBody User user) {
        boolean existsUSer = userRepository.existsByEmail(user.getEmail());
        if (existsUSer) {
            userRepository.saveAndFlush(user);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new InternException("Cannot use given email address", HttpStatus.CONFLICT);
        }
    }
}
