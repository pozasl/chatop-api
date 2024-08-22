package com.chatop.api.model;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class Rental extends GenericModel<Rental>{

    @NotBlank
    private String name;

    @PositiveOrZero
    private int surface;

    @PositiveOrZero
    private int price;

    @URL
    private String picture;

    @NotNull
    private String description;

    @Positive
    private int ownerId;

    public Rental() {
        super(Rental.class);
    }

    public String getName() {
        return name;
    }

    public Rental setName(String name) {
        this.name = name;
        return this;
    }

    public int getSurface() {
        return surface;
    }

    public Rental setSurface(int surface) {
        this.surface = surface;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Rental setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Rental setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Rental setDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonGetter("owner_id")
    public int getOwnerId() {
        return ownerId;
    }

    @JsonSetter("owner_id")
    public Rental setOwnerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

}
