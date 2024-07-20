package com.enes.fitnes_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.enes.fitnes_api.dto.LoginDto;
import com.enes.fitnes_api.dto.RegisterDto;
import com.enes.fitnes_api.expectations.AllReadyExists;
import com.enes.fitnes_api.expectations.NotFoundExpection;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.UserRepository;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtServices jwtServices;

    public User registerUser(RegisterDto registerDto) {
        try {
            User user = new User();
            user.setEmail(registerDto.getEmail());
            user.setFullName(registerDto.getFullName());
            user.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
            User userFetched = userRepository.save(user);
            return userFetched;
        } catch (Exception e) {
            throw new AllReadyExists("User Already Exists");
        }
    }

    public String login(LoginDto loginDto) {
        try {
            User user = userRepository.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new NotFoundExpection("User Not Found"));
            if (!bCryptPasswordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Invalid Password");
            }
            return jwtServices.generateToken(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
