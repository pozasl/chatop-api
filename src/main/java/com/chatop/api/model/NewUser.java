package com.chatop.api.model;

public class NewUser {

  private String name;
  private String email;
  private String password;

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
