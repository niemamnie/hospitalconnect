package de.hackathon.hospitalconnect.rest.user;


import de.hackathon.hospitalconnect.objects.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping
public class UserControllerMapping {
    private final UserController userController;

    public UserControllerMapping(UserController userController) {
        this.userController = userController;
    }

    @GetMapping("get/user")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity(userController.getUsers(), HttpStatus.OK);
    }


    @PutMapping("put/user")
    public ResponseEntity saveNewHospital(@RequestBody User user) {
        Long id = userController.saveNewHospital(user);
        return new ResponseEntity("/user/" + id.toString(), HttpStatus.CREATED);
    }

    @GetMapping("get/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userController.getUser(id), HttpStatus.OK);
    }
}
