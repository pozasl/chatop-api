package com.chatop.api.model;

import org.springframework.stereotype.Component;

import com.chatop.api.entity.RentalEntity;

@Component
public class RentalMapper extends GenericEntityToModelMapper<RentalEntity, Rental>{
    public Rental entityToModel(RentalEntity rentalEntity) {
        Rental rental = super.entityToModel(rentalEntity, new Rental());
        rental.setOwnerId(rentalEntity.getUser().getId());
        return rental;
    }
}
