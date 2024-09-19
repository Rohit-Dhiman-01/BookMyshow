package com.scaler.BookMyshow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.scaler.BookMyshow.controller.UserController;
import com.scaler.BookMyshow.dto.SignUpRequestDTO;

@EnableJpaAuditing
@SpringBootApplication
public class BookMyshowApplication implements CommandLineRunner{

	@Autowired
	private UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookMyshowApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
		signUpRequestDTO.setEmail("user@gmail.com");
		signUpRequestDTO.setPassword("password");
		userController.signUp(signUpRequestDTO);
    }

}
