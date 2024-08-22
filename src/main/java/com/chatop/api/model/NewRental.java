package com.chatop.api.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class NewRental {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 255, message = "{validation.name.size.too_long}")
    private String name;

    @PositiveOrZero(message = "Surface must be a positive number")
    private int surface;

    @PositiveOrZero(message = "Price must be a positive number")
    private int price;

    private MultipartFile picture;

    @NotNull(message = "Missing description parameter")
    @Size(max = 2000, message = "{validation.name.size.too_long}")
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

    public MultipartFile getPicture() {
        return picture;
    }

    public NewRental setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewRental setDescription(String description) {
        this.description = description;
        return this;
    }
    
}
