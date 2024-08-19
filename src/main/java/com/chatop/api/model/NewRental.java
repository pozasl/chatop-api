package com.chatop.api.model;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class NewRental {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @PositiveOrZero(message = "Surface must be a positive number")
    private int surface;

    @PositiveOrZero(message = "Price must be a positive number")
    private int price;

    @URL(message = "Must be a valid URL")
    private String picture;

    @NotNull(message = "Missing description parameter")
    private String description;

    public String getName() {
        return name;
    }

    public NewRental setName(String name) {
        this.name = name;
        return this;
    }

    public int getSurface() {
        return surface;
    }

    public NewRental setSurface(int surface) {
        this.surface = surface;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public NewRental setPrice(int price) {
        this.price = price;
        return this;
    }

    public String setPricture() {
        return picture;
    }

    public NewRental setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String setDescription() {
        return description;
    }

    public NewRental setDescription(String description) {
        this.description = description;
        return this;
    }
    
}
