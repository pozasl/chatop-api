package com.chatop.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.GenericEntityToModelMapper;
import com.chatop.api.model.NewRental;
import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalEntity;
import com.chatop.api.repository.RentalRepository;

@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;
    private GenericEntityToModelMapper<RentalEntity,Rental> entityToModelMapper;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, GenericEntityToModelMapper<RentalEntity,Rental> entityToModelMapper) {
        this.rentalRepository = rentalRepository;
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
    public void createRental(NewRental newRental) throws Exception{
        RentalEntity newEntity = new RentalEntity();
        BeanUtils.copyProperties(newRental, newEntity);
        rentalRepository.save(newEntity);
    }

    @Override
    public Rental getRentalById(int id)  throws Exception {
        return entityToModelMapper.entityToModel(this.foundEntityById(id), new Rental());
    }

    @Override
    public void saveRentalById(int id, Rental rental)  throws Exception {
        RentalEntity entity = this.foundEntityById(id);
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
