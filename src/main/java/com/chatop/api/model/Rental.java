package com.chatop.api.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Rental extends GenericModel<Rental>{

    private String name;
    private int surface;
    private int price;
    private String picture;
    private String description;

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

    public String setPricture() {
        return picture;
    }

    public Rental setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String setDescription() {
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
