package com.scaler.BookMyshow.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.scaler.BookMyshow.models.User;
import com.scaler.BookMyshow.respositones.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String email, String password){
        Optional<User> userOptional =userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new RuntimeException();
        }    
        User user = new User();
        user.setEmail(email);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User saveUser = userRepository.save(user);

        return saveUser;
    }

    public User logIn(String email, String password){
        Optional<User> userOptional =userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new RuntimeException();
        }   
        User user = userOptional.get(); 
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;            
        }
        throw  new RuntimeException();
    }
}
