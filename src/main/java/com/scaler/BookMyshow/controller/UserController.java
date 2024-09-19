package com.scaler.BookMyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.scaler.BookMyshow.dto.ResponseStatus;
import com.scaler.BookMyshow.dto.SignUpRequestDTO;
import com.scaler.BookMyshow.dto.SignUpResponsetDTO;
import com.scaler.BookMyshow.models.User;
import com.scaler.BookMyshow.services.UserService;
@Controller
public class UserController {
    private UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponsetDTO signUp(SignUpRequestDTO request){
        SignUpResponsetDTO signUpResponsetDTO = new SignUpResponsetDTO();
        User user;
        try {
            user=userService.signUp(request.getEmail(), request.getPassword());
            signUpResponsetDTO.setResponseStatus(ResponseStatus.SUCCESS);
            signUpResponsetDTO.setUserId(user.getId());
        } catch (Exception e) {
            signUpResponsetDTO.setResponseStatus(ResponseStatus.FAILURE);

        }    
        return signUpResponsetDTO;
    }
}
