package de.hackathon.hospitalconnect.rest.auth;

import de.hackathon.hospitalconnect.objects.User;
import de.hackathon.hospitalconnect.objects.repositories.UserRepository;
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
        userRepository.saveAndFlush(user);
        return new ResponseEntity(HttpStatus.OK);
    }
}
