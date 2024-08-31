package com.chatop.api.model;

import org.springframework.stereotype.Component;

import com.chatop.api.entity.RentalEntity;

@Component
public class RentalMapper extends AbstractEntityToModelMapper implements EntityToModelMapper<RentalEntity, Rental> {
    public Rental entityToModel(RentalEntity rentalEntity) {
        return new Rental(
            rentalEntity.getId(),
            rentalEntity.getName(),
            rentalEntity.getSurface(),
            rentalEntity.getPrice(),
            rentalEntity.getPicture(),
            rentalEntity.getDescription(),
            rentalEntity.getUser().getId(),
            convertDateForModel(rentalEntity.getCreationDate()),
            convertDateForModel(rentalEntity.getModificationDate())
        );
    }
}