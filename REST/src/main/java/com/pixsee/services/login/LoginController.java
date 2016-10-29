package com.pixsee.services.login;

import com.pixsee.services.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tudor on 28-Oct-16.
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(path = "/user",produces = MediaType.APPLICATION_JSON_VALUE,consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity logIn(User user) {
        User result = loginService.login(user).toBlocking().first();
        return ResponseEntity.ok(result);
    }
}
