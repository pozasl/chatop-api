package com.chatop.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.exception.UserAlreadyExistsException;
import com.chatop.api.model.AuthInfo;
import com.chatop.api.model.JwtInfo;
import com.chatop.api.model.NewUser;
import com.chatop.api.model.User;
import com.chatop.api.service.JwtService;
import com.chatop.api.service.JwtServiceImpl;
import com.chatop.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Register a new User")
    @PostMapping("/register")
    public JwtInfo register(@Valid @RequestBody NewUser newUser ) throws UserAlreadyExistsException {
        userService.createUser(newUser);
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(newUser.email(), newUser.password()));
        String token = jwtService.generateToken(authentication);
        return new JwtInfo(token);
    }

    @Operation(summary = "Login an existing user")
    @PostMapping("/login")
    public JwtInfo login(@Valid @RequestBody AuthInfo authInfo) throws AuthenticationException {
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(authInfo.email(), authInfo.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        return new JwtInfo(token);
    }

    @Operation(summary = "Get the logged in user information")
    @GetMapping("/me")
    public User me(Authentication auth) throws BadCredentialsException {
        if(Objects.isNull(auth))
            throw new BadCredentialsException("Couldn't authenticate");
        try {
            return userService.getUserByEmail(auth.getName());
        } catch (ResourceNotFoundException e) {
            throw new BadCredentialsException("Couldn't authenticate");
        }
    }
    
}
