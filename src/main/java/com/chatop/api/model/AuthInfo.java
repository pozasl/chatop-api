package com.chatop.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthInfo {
  @Email(message = "Not an email address")
  private String email;
  @NotBlank(message = "Password is mandatory")
  private String password;

  public AuthInfo(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "AuthInfo{ email='" + email + "', password='" + password + "' }";
  }
}