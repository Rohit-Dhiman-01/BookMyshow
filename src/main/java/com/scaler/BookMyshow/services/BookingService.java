package com.scaler.BookMyshow.services;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.scaler.BookMyshow.models.Booking;
import com.scaler.BookMyshow.models.BookingStatus;
import com.scaler.BookMyshow.models.Show;
import com.scaler.BookMyshow.models.ShowSeat;
import com.scaler.BookMyshow.models.ShowSeatStatus;
import com.scaler.BookMyshow.models.User;
import com.scaler.BookMyshow.respositones.BookingRepository;
import com.scaler.BookMyshow.respositones.ShowRepository;
import com.scaler.BookMyshow.respositones.ShowSeatRepository;
import com.scaler.BookMyshow.respositones.UserRepository;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;
    private PriceCalculator priceCalculator;
    
    @Autowired
    public BookingService(BookingRepository bookingRepository, PriceCalculator priceCalculator, ShowRepository showRepository, ShowSeatRepository showSeatRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.priceCalculator = priceCalculator;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }

    
    
    @SuppressWarnings("unlikely-arg-type")
    @Transactional(isolation=Isolation.SERIALIZABLE)
    // @Autowired
    public Booking bookMovie(Long userId, List<Long> seatsIds, Long showId){
        // get user form user id
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException();
        }
        User bookedByUser = userOptional.get();
        // get show form show id
        Optional<Show> showOptional = showRepository.findById(showId);
        if (showOptional.isEmpty()) {
            throw new RuntimeException();
        }

        Show bookedForShow = showOptional.get();
        // --- START TRANSATION HERE---
        // get show seat form show seat id
        List<ShowSeat> showSeats=showSeatRepository.findAllById(seatsIds);
        
        // check if all seat are in available state or not.
        for (ShowSeat showSeat : showSeats) {
            if(!(showSeat.equals(ShowSeatStatus.AVAILABLE)) || 
            showSeat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED) && 
            (Duration.between(showSeat.getBlockedAt().toInstant(), new Date().toInstant()).toMinutes()>15)
            ){
                // if no, throw error 
                throw new RuntimeException();
            }         
        } 
        // if yes mark status as lock
        // save and upload the show seats id to db
        List<ShowSeat> savShowSeats= new ArrayList<>();

        for (ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOKED);
            showSeat.setBlockedAt(new Date());
            savShowSeats.add(showSeatRepository.save(showSeat));
            
        }
        // -- END TRANSATION HERE ---
        // create corroseponding booking object.

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(savShowSeats);
        booking.setUser(bookedByUser);
        booking.setShow(bookedForShow);
        booking.setBookedAt(new Date());
        booking.setAmount(priceCalculator.calculatorPrice(savShowSeats, bookedForShow));
        
        Booking saveBooking = bookingRepository.save(booking);
        
        return saveBooking;
    }
}
