package com.chatop.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewUser {

  @NotBlank(message = "Name is mandatory")
  private String name;

  @Email(message = "Email is mandatory")
  private String email;

  @Size(min = 6, message = "Password must be at least 6 character long")
  private String password;

  NewUser (String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public NewUser setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public NewUser setName(String name) {
    this.name = name;
    return this;
  }

  public String getPassword() {
    return this.password;
  }

  public NewUser setPassword(String password) {
    this.password = password;
    return this;
  }

}
