package com.chatop.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.AccessDeniedException;

import com.chatop.api.entity.RentalEntity;
import com.chatop.api.entity.UserEntity;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.NewRental;
import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalMapper;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class RentalServiceImplUnitTest {

    @Mock
    RentalRepository rentalRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    RentalMapper rentalMapper;

    @InjectMocks
    RentalServiceImpl rentalService;

    private  DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    @Test
    void getAllRentalsShouldWork() {
        
        UserEntity bob = new UserEntity();
        bob.setId(1);
        RentalEntity entity1 = new RentalEntity();
        entity1.setId(1);
        entity1.setName("entity1");
        entity1.setSurface(50);
        entity1.setPrice(200);
        entity1.setDescription("description 1");
        entity1.setPicture("upload/test1.jpg");
        entity1.setUser(bob);
        RentalEntity entity2 = new RentalEntity();
        entity2.setId(2);
        entity2.setName("entity2");
        entity2.setSurface(55);
        entity2.setPrice(220);
        entity2.setDescription("description 2");
        entity2.setPicture("upload/test2.jpg");
        entity2.setUser(bob);
        List<RentalEntity> entities = List.of(entity1, entity2);
        Mockito.when(rentalRepository.findAll()).thenReturn(entities);
        List<Rental> rentals = List.of(new Rental(
            1,
            "entity1",
            50,
            200,
            "upload/test1.jpg",
            "description 1",
            1,
            "2024/08/27",
            "2024/08/27"
        ), new Rental(
                2,
                "entity2",
                55,
                220,
                "upload/test2.jpg",
                "description 2",
                1,
                "2024/08/27",
                "2024/08/27"
            )
        );
        Mockito.when(rentalMapper.entityToModel(entities.get(0))).thenReturn(rentals.get(0));
        Mockito.when(rentalMapper.entityToModel(entities.get(1))).thenReturn(rentals.get(1));
        assertEquals(rentals, rentalService.getAllRentals());
    }

    @Test
    void createRentalShouldWork() throws Exception{
        InputStream jpgStream = this.getClass().getClassLoader().getResourceAsStream("img/test.jpg");
        MockMultipartFile file = new MockMultipartFile(
            "picture", 
            "test.jpg", 
            MediaType.IMAGE_JPEG_VALUE,
            jpgStream
        );
        NewRental newRental = new NewRental("rental", 20, 120, file, "desc");
        UserEntity user = new UserEntity();
        user.setId(1);
        RentalEntity savedRental = new RentalEntity();
        savedRental.setUser(user);
        savedRental.setPicture("upload/test.jpg");
        Mockito.when(userRepository.findByEmail("bob@test.com")).thenReturn(user);
        Mockito.when(rentalRepository.save(any())).thenReturn(savedRental);
        Mockito.when(rentalMapper.entityToModel(savedRental)).thenReturn(
            new Rental(
                user.getId(),
                "rental",
                20,
                120,
                "upload/test.jpg",
                "desc",
                1,
                "2024/08/27",
                "2024/08/27"
            )
        );
        Rental rental = rentalService.createRental(newRental, "bob@test.com", "upload/test.jpg");
        assertEquals(rental.ownerId(),user.getId());
        assertEquals("upload/test.jpg", rental.picture());
    }

    @Test
    void getRentalByIdShouldWork() throws Exception{
        Date now = df.parse("2024/08/25");
        UserEntity bob = new UserEntity();
        bob.setId(1);
        RentalEntity entity1 = new RentalEntity();
        entity1.setId(1);
        entity1.setName("entity1");
        entity1.setSurface(50);
        entity1.setPrice(200);
        entity1.setDescription("description 1");
        entity1.setPicture("upload/test1.jpg");
        entity1.setUser(bob);
        entity1.setCreationDate(now);
        entity1.setModificationDate(now);
        Mockito.when(rentalRepository.findById(1)).thenReturn(Optional.of(entity1));
        Rental rental = new Rental(
            1,
            "entity1",
            50,
            200,
            "upload/test1.jpg",
            "description 1",
            1,
            "2024/08/25",
            "2024/08/25"
        );
        Mockito.when(rentalMapper.entityToModel(entity1)).thenReturn(rental);
        assertEquals(rentalService.getRentalById(1), rental);
    }

    @Test
    void getRentalByIdShouldTrow() throws ResourceNotFoundException{
        Mockito.when(rentalRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> rentalService.getRentalById(1),"Unknown rental id");
    }

    @Test
    void saveRentalByIdShouldWork() throws Exception{
        NewRental newRental = new NewRental("rental", 20, 120, null, "desc");
        Date now = df.parse("2024/08/25");
        UserEntity bob = new UserEntity();
        bob.setId(1);
        bob.setEmail("bob@test.com");
        RentalEntity entity1 = new RentalEntity();
        entity1.setId(1);
        entity1.setName("entity1");
        entity1.setSurface(50);
        entity1.setPrice(200);
        entity1.setDescription("description 1");
        entity1.setPicture("upload/test1.jpg");
        entity1.setUser(bob);
        entity1.setCreationDate(now);
        entity1.setModificationDate(now);
        Mockito.when(rentalRepository.findById(1)).thenReturn(Optional.of(entity1));
        Mockito.when(userRepository.findByEmail(bob.getEmail())).thenReturn(bob);
        Rental rental = new Rental(
            1,
            "rental",
            20,
            120,
            "upload/test1.jpg",
            "desc",
            1,
            "2024/08/25",
            "2024/08/25"
        );
        Mockito.when(rentalMapper.entityToModel(any())).thenReturn(rental);
        assertEquals(rentalService.saveRentalById(1, newRental, bob.getEmail()), rental);
    }

    @Test
    void saveRentalByIdShouldThrowIfUnknownId() throws Exception{
        NewRental newRental = new NewRental("rental", 20, 120, null, "desc");
        Date now = df.parse("2024/08/25");
        UserEntity bob = new UserEntity();
        bob.setId(1);
        bob.setEmail("bob@test.com");
        RentalEntity entity1 = new RentalEntity();
        entity1.setId(1);
        entity1.setName("entity1");
        entity1.setSurface(50);
        entity1.setPrice(200);
        entity1.setDescription("description 1");
        entity1.setPicture("upload/test1.jpg");
        entity1.setUser(bob);
        entity1.setCreationDate(now);
        entity1.setModificationDate(now);
        Mockito.when(rentalRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(
            ResourceNotFoundException.class,
            () -> rentalService.saveRentalById(1, newRental, "bob@test.com"),
            "Unknown rental id"
        );
    }

    @Test
    void saveRentalByIdShouldThrowIfNotOwner() throws Exception{
        NewRental newRental = new NewRental("rental", 20, 120, null, "desc");
        Date now = df.parse("2024/08/25");
        UserEntity bob = new UserEntity();
        bob.setId(1);
        bob.setEmail("bob@test.com");
        UserEntity alice = new UserEntity();
        alice.setId(2);
        alice.setEmail("alice@test.com");
        RentalEntity entity1 = new RentalEntity();
        entity1.setId(1);
        entity1.setName("entity1");
        entity1.setSurface(50);
        entity1.setPrice(200);
        entity1.setDescription("description 1");
        entity1.setPicture("upload/test1.jpg");
        entity1.setUser(bob);
        entity1.setCreationDate(now);
        entity1.setModificationDate(now);
        Mockito.when(rentalRepository.findById(1)).thenReturn(Optional.of(entity1));
        Mockito.when(userRepository.findByEmail(alice.getEmail())).thenReturn(alice);
        assertThrows(
            AccessDeniedException.class,
            () -> rentalService.saveRentalById(1, newRental, "alice@test.com"),
            "Not the rental owner"
        );
    }
}
