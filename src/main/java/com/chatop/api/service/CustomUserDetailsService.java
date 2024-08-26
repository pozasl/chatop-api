package com.chatop.api.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chatop.api.entity.UserEntity;
import com.chatop.api.model.CustomUserDetails;
import com.chatop.api.repository.UserRepository;

/**
 * Custom Userdetails service to use email instead of username for authentication
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByEmail(email);
        if (Objects.isNull(entity)) 
            throw new UsernameNotFoundException("Unknown Email address");
       return new CustomUserDetails(entity);
    }

}
