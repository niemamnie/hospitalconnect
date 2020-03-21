package de.hackathon.hospitalconnect.rest.user;


import de.hackathon.hospitalconnect.objects.hospitals.User;
import de.hackathon.hospitalconnect.rest.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity saveNewHospital(@RequestBody User user) {
        Long id = userService.saveNewHospital(user);
        return new ResponseEntity("/user/" + id.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
}
