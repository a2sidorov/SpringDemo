package dev.a2.springdemo.service;


import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

import dev.a2.springdemo.model.User;
import dev.a2.springdemo.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
	
	User findByEmail(String email);
	
    User save(UserRegistrationDto userDto);
    
}
