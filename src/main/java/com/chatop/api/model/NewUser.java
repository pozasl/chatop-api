package com.chatop.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewUser {

  @NotBlank(message = "Name is mandatory")
  @Size(max = 255, message = "{validation.name.size.too_long}")
  private String name;

  @Email(message = "Invalid email")
  @Size(max = 255, message = "{validation.name.size.too_long}")
  private String email;

  @Size(min = 6, message = "Password must be at least 6 characters long")
  @Size(max = 255, message = "{validation.name.size.too_long}")
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
