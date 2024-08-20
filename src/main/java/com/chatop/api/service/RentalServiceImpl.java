package com.chatop.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.GenericEntityToModelMapper;
import com.chatop.api.model.NewRental;
import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalEntity;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;
    private UserRepository userRepository;
    private GenericEntityToModelMapper<RentalEntity,Rental> entityToModelMapper;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, UserRepository userRepository, GenericEntityToModelMapper<RentalEntity,Rental> entityToModelMapper) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.entityToModelMapper = entityToModelMapper;
    }

    @Override
    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        rentalRepository.findAll().forEach(
            e ->  rentals.add(entityToModelMapper.entityToModel(e, new Rental()))
        );
        return rentals;
    }

    @Override
    public Rental createRental(NewRental newRental, String userEmail, String imgSrc) throws Exception{
        RentalEntity newEntity = new RentalEntity();
        BeanUtils.copyProperties(newRental, newEntity);
        newEntity.setUser(userRepository.findByEmail(userEmail));
        newEntity.setPicture(imgSrc);
        return entityToModelMapper.entityToModel(rentalRepository.save(newEntity), new Rental());
    }

    @Override
    public Rental getRentalById(int id)  throws Exception {
        RentalEntity entity = this.foundEntityById(id);
        Rental rental =  entityToModelMapper.entityToModel(entity, new Rental());
        rental.setOwnerId(entity.getUser().getId());
        return rental;
    }

    @Override
    public void saveRentalById(int id, Rental rental, String userEmail)  throws Exception {
        RentalEntity entity = this.foundEntityById(id);
        if (entity.getUser() != userRepository.findByEmail(userEmail))
            throw new AccessDeniedException("Not the rental owner");
        BeanUtils.copyProperties(rental, entity);
        rentalRepository.save(entity);
    }

    @Override
    public List<Rental> getRentalsByUserId() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRentalsByUserId'");
    }
    
    private RentalEntity foundEntityById(int id) throws Exception{
        Optional<RentalEntity> foundRental = rentalRepository.findById(id);
        if(foundRental.isEmpty())
            throw new ResourceNotFoundException("Unknown rental id");
        return foundRental.get();
    }
}
