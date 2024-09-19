package com.scaler.BookMyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponsetDTO {
    private ResponseStatus responseStatus;
    private Long userId;
}
