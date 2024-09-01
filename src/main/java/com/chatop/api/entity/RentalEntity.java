package com.chatop.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

/**
 * Rental entity.
 */
@Data
@Entity
@Table(name = "rentals")
public class RentalEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int id;

  private String name;

  private int surface;

  private int price;

  private String picture;

  @Length(max = 2000)
  private String description;

  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  private UserEntity user;

  @Column(name = "created_at")
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  protected Date creationDate;

  @Column(name = "updated_at")
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  protected Date modificationDate;

}
