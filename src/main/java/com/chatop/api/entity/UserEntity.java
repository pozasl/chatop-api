package com.chatop.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Application's user entity.
 */
@Data
@Entity
@Table(name = "users", indexes = @Index(name = "users_index", columnList = "email", unique = true))
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int id;

  @Column(unique = true)
  private String email;

  private String name;

  private String password;

  @Column(name = "created_at")
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  protected Date creationDate;

  @Column(name = "updated_at")
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)  
  
  protected Date modificationDate;

}
