package de.hackathon.hospitalconnect.rest.user;


import de.hackathon.hospitalconnect.model.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("get/user")// serverdomanm.com/get/user
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }


    @PostMapping("post/user")
    public ResponseEntity saveNewHospital(@RequestBody User user) {
        Long id = userService.saveNewUser(user);
        return new ResponseEntity("/user/" + id.toString(), HttpStatus.CREATED);
    }

    @GetMapping("get/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PatchMapping("/patch/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchUser(@PathVariable Long id, @RequestBody User userWichChanges) {
        userService.patchUser(id, userWichChanges);
    }

    @PatchMapping("/settings/admin/set/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setAdmin(@PathVariable Long id) {
        userService.setAdmin(id);
    }
}
