package com.chatop.api.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class User {
  private int id;
  private String name;
  private String email;

  @JsonProperty("created_at")
  private String created;

  @JsonProperty("updated_at")
  private String updated;

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

  @JsonGetter("created_at")
  public String getCreated() {
    return this.created;
  }

  @JsonSetter("created_at")
  public User setCreated(String creationDateStr) {
    this.created = creationDateStr;
    return this;
  }

  @JsonGetter("updated_at")
  public String getUpdated() {
    return this.updated;
  }

  @JsonSetter("updated_at")
  public User setUpdated(String modificationDateStr) {
    this.updated = modificationDateStr;
    return this;
  }
}
