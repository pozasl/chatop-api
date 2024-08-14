package com.chatop.api.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "USERS")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String email;
  private String name;
  private String password;

  @Column(name = "created_at")
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Column(name = "updated_at")
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;

  public int getId() {
    return this.id;
  }

  public UserEntity setId(int id) {
    this.id = id;
    return this;
  }

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

  public Date getCreationDate() {
    return this.creationDate;
  }

  public UserEntity setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  public Date getModificationDate() {
    return this.modificationDate;
  }

  public UserEntity setModificationDate(Date modificationDate) {
    this.modificationDate = modificationDate;
    return this;
  }

}
