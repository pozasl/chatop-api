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
        this(null, 0, 0, null, null);
    }

    public RentalEntity(String name, int surface, int price, String picture, String desciption) {
        super(RentalEntity.class);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String name;

    private int surface;

    private int price;

    private String picture;

    private String description;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private UserEntity user;

    public String getName() {
        return name;
    }

    public RentalEntity setName(String name) {
    this.name = name;
        return this;
    }

    public int getSurface() {
        return this.surface;
    }

    public RentalEntity setSurface(int surface) {
        this.surface = surface;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public RentalEntity setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public RentalEntity setPicture(String picture) {
    this.picture = picture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RentalEntity setDescription(String description) {
        this.description = description;
        return this;
    }

}
