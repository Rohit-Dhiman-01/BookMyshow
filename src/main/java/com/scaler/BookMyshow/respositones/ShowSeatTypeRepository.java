package com.scaler.BookMyshow.respositones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.BookMyshow.models.Show;
import com.scaler.BookMyshow.models.ShowSeatType;
@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long>{
    
    List<ShowSeatType> findAllByShow(Show show);   
}
