package com.chatop.api.model;

import java.util.Date;

public class User {
  private int id;
  private String name;
  private String email;
  private Date created;
  private Date modiied;

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

  public Date getCreated() {
    return this.created;
  }

  public User setCreated(Date creationDate) {
    this.created = creationDate;
    return this;
  }

  public Date getModified() {
    return this.modiied;
  }

  public User setModified(Date modificationDate) {
    this.modiied = modificationDate;
    return this;
  }
}
