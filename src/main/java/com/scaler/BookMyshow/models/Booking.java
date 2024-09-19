package com.scaler.BookMyshow.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    private BookingStatus bookingStatus;
    
    @ManyToMany
    private List<ShowSeat> showSeats;
    
    @ManyToOne
    private User user;
    private Date bookedAt;
    
    @ManyToOne
    private Show show;
    private int amount;

    @OneToMany
    private List<Payment> payments;

}
