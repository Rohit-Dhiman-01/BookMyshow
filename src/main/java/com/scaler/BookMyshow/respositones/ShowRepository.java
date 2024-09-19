package com.scaler.BookMyshow.respositones;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.BookMyshow.models.Show;
@Repository
public interface ShowRepository extends JpaRepository<Show, Long>{

    @SuppressWarnings("null")
    @Override
    public Optional<Show> findById(Long id);
    
}
