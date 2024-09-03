package com.chatop.api.controller;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.User;
import com.chatop.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Get a User by its id")
  @SecurityRequirement(name = "Authorization")
  @GetMapping("/{id}")
  public User getUserById(@PathVariable int id) throws ResourceNotFoundException {
    return this.userService.getUserById(id);
  }

}
