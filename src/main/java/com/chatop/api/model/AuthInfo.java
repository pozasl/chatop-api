package com.chatop.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthInfo(
  @NotBlank(message = "Email address is mandatory")
  @Email(message = "Not an email address")
  String email,
  @NotBlank(message = "Password is mandatory")
  String password) {}