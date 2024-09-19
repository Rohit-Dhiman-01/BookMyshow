package com.scaler.BookMyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingMoveResponseDTO {
    private ResponseStatus responseStatus;
    private int amount;
    private Long bookingId;
}
