package com.chatop.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rentals")
public class RentalEntity extends GenericEntity<RentalEntity>{

    public RentalEntity() {
        super(RentalEntity.class);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;

    private Float surface;

    private Float price;

    private String picture;

    private String description;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private UserEntity user;
  
}
