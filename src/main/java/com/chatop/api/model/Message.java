package com.chatop.api.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Message {

    @NotBlank
    private String message;

    @NotNull
    @JsonProperty("user_id")
    private int userId;

    @NotNull
    @JsonProperty("rental_id")
    private int rentalId;

    public String getMessage() {
        return message;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonGetter("user_id")
    public int getUserId() {
        return userId;
    }

    @JsonSetter("user_id")
    public Message setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    @JsonGetter("rental_id")
    public int getRentalId() {
        return rentalId;
    }

    @JsonSetter("rental_id")
    public Message setRentalId(int rentalId) {
        this.rentalId = rentalId;
        return this;
    }
}
