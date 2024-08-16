package com.chatop.api.controller;

import java.util.Objects;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.AuthInfo;
import com.chatop.api.model.JwtInfo;
import com.chatop.api.model.NewUser;
import com.chatop.api.model.User;
import com.chatop.api.service.JwtService;
import com.chatop.api.service.JwtServiceImpl;
import com.chatop.api.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtService jwtService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtServiceImpl jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public JwtInfo register(@Valid @RequestBody NewUser newUser ) throws Exception {
        userService.createUser(newUser);
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(newUser.getEmail(), newUser.getPassword()));
        String token = jwtService.generateToken(authentication);
        return new JwtInfo(token);
    }

    @PostMapping("/login")
    public JwtInfo login(@Valid @RequestBody AuthInfo authInfo) throws Exception {
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(authInfo.getEmail(), authInfo.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        return new JwtInfo(token);
    }

    @GetMapping("/me")
    public User me(Authentication auth) throws Exception {
        if(Objects.isNull(auth)) throw new AuthenticationException("Couldn't authenticate");
      return userService.getUserByEmail(auth.getName());
    }
    
}
