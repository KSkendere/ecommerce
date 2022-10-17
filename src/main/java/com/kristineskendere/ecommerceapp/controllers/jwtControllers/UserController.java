package com.kristineskendere.ecommerceapp.controllers.jwtControllers;

import com.kristineskendere.ecommerceapp.models.authModels.User;
import com.kristineskendere.ecommerceapp.services.authServices.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping({"/registerNewUser"})
    ResponseEntity<User> registerNewUser(@RequestBody @Valid User user){
        User savedUser = userService.registerNewUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
