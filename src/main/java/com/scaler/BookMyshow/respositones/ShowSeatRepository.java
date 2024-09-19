package com.scaler.BookMyshow.respositones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.BookMyshow.models.ShowSeat;
@Repository
public interface ShowSeatRepository extends  JpaRepository<ShowSeat, Long>{

    @SuppressWarnings("null")
    @Override
    List<ShowSeat> findAllById(Iterable<Long> ids);

    @SuppressWarnings({ "null", "unchecked" })
    @Override
    ShowSeat save(ShowSeat entity); // try to fine showseats by id, if found then update it otherwise insert it. 
    
}
