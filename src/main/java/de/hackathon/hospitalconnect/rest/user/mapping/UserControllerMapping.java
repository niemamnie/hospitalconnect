package de.hackathon.hospitalconnect.rest.user.mapping;


import de.hackathon.hospitalconnect.objects.hospitals.User;
import de.hackathon.hospitalconnect.rest.user.UserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/user")
public class UserControllerMapping {
    private final UserController userController;

    public UserControllerMapping(UserController userController) {
        this.userController = userController;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity(userController.getUsers(), HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity saveNewHospital(@RequestBody User user) {
        Long id = userController.saveNewHospital(user);
        return new ResponseEntity("/user/" + id.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userController.getUser(id), HttpStatus.OK);
    }
}
