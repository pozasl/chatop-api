package com.chatop.api.model;

import com.chatop.api.entity.RentalEntity;
import org.springframework.stereotype.Component;

/**
 * Rental entity to model mapper.
 */
@Component
public class RentalMapper
    extends AbstractEntityToModelMapper
    implements EntityToModelMapper<RentalEntity, Rental> {
  
  /**
   * Convert entity to model.
   */
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
        convertDateForModel(rentalEntity.getModificationDate()));
  }
}