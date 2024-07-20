package com.enes.fitnes_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.enes.fitnes_api.dto.RegisterDto;
import com.enes.fitnes_api.expectations.AllReadyExists;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.UserRepository;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerUser(RegisterDto registerDto) {
        try {
            User user = new User();
            user.setEmail(registerDto.getEmail());
            user.setFullName(registerDto.getFullName());
            user.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
            return userRepository.save(user);
        } catch (Exception e) {
            throw new AllReadyExists(e.getMessage(), e);
        }
    }
}
