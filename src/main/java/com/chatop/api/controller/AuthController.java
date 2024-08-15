package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.AuthInfo;
import com.chatop.api.model.NewUser;
import com.chatop.api.model.User;
import com.chatop.api.service.AuthService;
import com.chatop.api.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;
    private AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody NewUser newUser ) throws Exception {
        System.out.println(newUser.toString());
        return userService.entityToModel(userService.createUser(newUser));
    }

    @PostMapping("/login")
        public User login(@RequestBody AuthInfo authInfo) throws Exception {
            return authService.authenticate(authInfo);
    }

    // @GetMapping("/me")
    //     public User me(@RequestHeader  ) throws Exception {
    // }
    
}
