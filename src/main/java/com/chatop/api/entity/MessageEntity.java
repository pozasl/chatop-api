package com.chatop.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class MessageEntity  extends AbstractGenericEntity<MessageEntity> {

    private String message;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="rental_id", nullable = false)
    private RentalEntity rental;

    public MessageEntity() {
        this(null, null, null);
    }
        
    public MessageEntity(String message, UserEntity user, RentalEntity rental) {
        super(MessageEntity.class);
        this.message = message;
        this.user = user;
        this.rental = rental;
    }

    public String getMessage() {
        return this.message;
    }

    public MessageEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public MessageEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public RentalEntity getRental() {
        return this.rental;
    }

    public MessageEntity setRental(RentalEntity rental) {
        this.rental = rental;
        return this;
    }
}
