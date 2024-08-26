package com.chatop.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "users", indexes = @Index(name = "users_index", columnList = "email", unique = true))
public class UserEntity extends AbstractGenericEntity<UserEntity> {

  public UserEntity() {
     this(null, null, null);
  }

  public UserEntity(String name, String email, String password) {
    super(UserEntity.class);
    this.id = -1;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  @Column(unique = true)
  private String email;

  private String name;

  private String password;

  public String getEmail() {
    return this.email;
  }

  public UserEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public UserEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getPassword() {
    return this.password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public String toString() {
    return "UserEntity{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", password='"
      + password + '\'' + '}';
  }

}
