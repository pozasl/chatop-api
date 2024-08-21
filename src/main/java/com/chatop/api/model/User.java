package com.chatop.api.model;

public class User extends GenericModel<User> {
  private String name;
  private String email;

  public User() {
    super(User.class);
  }

  public String getEmail() {
    return this.email;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public User setName(String name) {
    this.name = name;
    return this;
  }
  
}
