package com.chatop.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.chatop.api.entity.RentalEntity;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewRental;
import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalMapper;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;
    private UserRepository userRepository;
    private RentalMapper entityToModelMapper;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, UserRepository userRepository, RentalMapper entityToModelMapper) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.entityToModelMapper = entityToModelMapper;
    }

    @Override
    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        rentalRepository.findAll().forEach(
            e ->  {
                Rental rental = entityToModelMapper.entityToModel(e);
                rental.setOwnerId(e.getUser().getId());
                rentals.add(rental);
            }
        );
        return rentals;
    }

    @Override
    public Rental createRental(NewRental newRental, String userEmail, String imgSrc) throws Exception{
        RentalEntity newEntity = new RentalEntity();
        BeanUtils.copyProperties(newRental, newEntity);
        newEntity.setUser(userRepository.findByEmail(userEmail));
        newEntity.setPicture(imgSrc);
        return entityToModelMapper.entityToModel(rentalRepository.save(newEntity));
    }

    @Override
    public Rental getRentalById(int id)  throws Exception {
        RentalEntity entity = this.foundEntityById(id);
        Rental rental =  entityToModelMapper.entityToModel(entity);
        rental.setOwnerId(entity.getUser().getId());
        return rental;
    }

    @Override
    public void saveRentalById(int id, NewRental rental, String userEmail)  throws Exception {
        RentalEntity entity = this.foundEntityById(id);
        if (entity.getUser().getId() != userRepository.findByEmail(userEmail).getId())
            throw new AccessDeniedException("Not the rental owner");
        BeanUtils.copyProperties(rental, entity);
        rentalRepository.save(entity);
    }
    
    private RentalEntity foundEntityById(int id) throws Exception{
        Optional<RentalEntity> foundRental = rentalRepository.findById(id);
        if(foundRental.isEmpty())
            throw new ResourceNotFoundException("Unknown rental id");
        return foundRental.get();
    }
}
