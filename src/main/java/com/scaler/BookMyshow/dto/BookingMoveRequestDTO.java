package com.scaler.BookMyshow.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingMoveRequestDTO {
    private List<Long> showseatIds;
    private Long userId;
    private Long showId;

}
