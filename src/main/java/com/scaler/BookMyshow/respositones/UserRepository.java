package com.scaler.BookMyshow.respositones;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.BookMyshow.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @SuppressWarnings("null")
    @Override
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
