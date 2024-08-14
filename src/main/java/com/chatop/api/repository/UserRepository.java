package com.chatop.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
