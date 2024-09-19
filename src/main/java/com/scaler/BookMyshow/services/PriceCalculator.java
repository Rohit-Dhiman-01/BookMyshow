package com.scaler.BookMyshow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scaler.BookMyshow.models.Show;
import com.scaler.BookMyshow.models.ShowSeat;
import com.scaler.BookMyshow.models.ShowSeatType;
import com.scaler.BookMyshow.respositones.ShowSeatTypeRepository;
@Service
public class PriceCalculator {
    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    
    public int calculatorPrice(List<ShowSeat> showSeats, Show bookedForShow){
        List<ShowSeatType> showSeatTypes =showSeatTypeRepository.findAllByShow(bookedForShow);
        int amount=0;
        for (ShowSeat showSeat : showSeats) {
            for (ShowSeatType showSeatType : showSeatTypes) {
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }

        return amount;
    }
}
