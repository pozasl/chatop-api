package com.chatop.api.service;

import com.chatop.api.entity.RentalEntity;
import com.chatop.api.entity.UserEntity;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewRental;
import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalMapper;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

/**
 * Rental service implementation.
 */
@Service
public class RentalServiceImpl implements RentalService {

  private RentalRepository rentalRepository;
  private UserRepository userRepository;
  private RentalMapper rentalMapper;

  /**
   * Rental service.
   *
   * @param rentalRepository users' rental repository
   * @param userRepository users repository
   * @param rentalMapper rental entity to model mapper
   */
  @Autowired
  public RentalServiceImpl(RentalRepository rentalRepository, UserRepository userRepository,
      RentalMapper rentalMapper) {
    this.rentalRepository = rentalRepository;
    this.userRepository = userRepository;
    this.rentalMapper = rentalMapper;
  }

  @Override
  public List<Rental> getAllRentals() {
    List<Rental> rentals = new ArrayList<>();
    rentalRepository.findAll().forEach(
        e -> rentals.add(rentalMapper.entityToModel(e)));
    return rentals;
  }

  @Override
  public Rental createRental(NewRental newRental, String userEmail, String imgSrc) {

    RentalEntity newEntity = new RentalEntity();
    BeanUtils.copyProperties(newRental, newEntity);
    newEntity.setUser(userRepository.findByEmail(userEmail).orElseThrow(
        () -> new ResourceNotFoundException("unkown user")));
    newEntity.setPicture(imgSrc);
    return rentalMapper.entityToModel(rentalRepository.save(newEntity));
  }

  @Override
  public Rental getRentalById(int id) throws ResourceNotFoundException {
    RentalEntity entity = this.getEntityById(id);
    return rentalMapper.entityToModel(entity);
  }

  @Override
  public Rental saveRentalById(int id, NewRental rental, String userEmail)
      throws AccessDeniedException, ResourceNotFoundException {
    UserEntity user = userRepository.findByEmail(userEmail).orElseThrow(
        () -> new ResourceNotFoundException("unkown user"));
    RentalEntity entity = this.getEntityById(id);
    if (user.getId() != entity.getUser().getId()) {
      throw new AccessDeniedException("not the rental owner");
    }
    BeanUtils.copyProperties(rental, entity);
    return rentalMapper.entityToModel(rentalRepository.save(entity));
  }

  private RentalEntity getEntityById(int id) throws ResourceNotFoundException {
    return rentalRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Unknown rental id"));
  }
}
