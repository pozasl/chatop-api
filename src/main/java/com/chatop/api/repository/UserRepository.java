package com.chatop.api.repository;

import com.chatop.api.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  public Optional<UserEntity> findByEmail(@Param("email") String email);
}
