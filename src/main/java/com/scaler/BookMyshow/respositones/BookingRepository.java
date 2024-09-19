package com.scaler.BookMyshow.respositones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.BookMyshow.models.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @SuppressWarnings({ "null", "unchecked" })
    @Override
    Booking save(Booking entity);
}
