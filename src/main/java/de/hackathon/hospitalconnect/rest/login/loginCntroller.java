package de.hackathon.hospitalconnect.rest.login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginCntroller {
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login() {
    }
}
