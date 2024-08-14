package com.chatop.api.model;

public class User {
  private int id;
  private String name;
  private String email;
  private String created_at;
  private String updated_at;

  public int getId() {
    return this.id;
  }

  public User setId(int id) {
    this.id = id;
    return this;
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

  public String getCreatedAt() {
    return this.created_at;
  }

  public User setCreatedAt(String creationDateStr) {
    this.created_at = creationDateStr;
    return this;
  }

  public String getUpdatedAt() {
    return this.updated_at;
  }

  public User setUpdatedAt(String modificationDateStr) {
    this.updated_at = modificationDateStr;
    return this;
  }
}
