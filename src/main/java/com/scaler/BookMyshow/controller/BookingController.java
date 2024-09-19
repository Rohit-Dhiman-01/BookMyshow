package com.scaler.BookMyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.scaler.BookMyshow.dto.BookingMoveRequestDTO;
import com.scaler.BookMyshow.dto.BookingMoveResponseDTO;
import com.scaler.BookMyshow.dto.ResponseStatus;
import com.scaler.BookMyshow.models.Booking;
import com.scaler.BookMyshow.services.BookingService;

@Controller
public class BookingController {

    private BookingService bookingService;
    
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

    private Booking booking;
    public BookingMoveResponseDTO bookMovie( BookingMoveRequestDTO request){
        BookingMoveResponseDTO bookingMoveRequestDTO = new BookingMoveResponseDTO();
        try {
            bookingService.bookMovie(request.getUserId(), request.getShowseatIds(), request.getShowId());
            bookingMoveRequestDTO.setBookingId(booking.getId());
            bookingMoveRequestDTO.setResponseStatus(ResponseStatus.SUCCESS);
            bookingMoveRequestDTO.setAmount(booking.getAmount());
        } catch (Exception e) {
            bookingMoveRequestDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return bookingMoveRequestDTO;
    }
}
