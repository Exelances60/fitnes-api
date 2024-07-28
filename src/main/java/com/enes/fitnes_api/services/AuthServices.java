package com.enes.fitnes_api.services;

import com.enes.fitnes_api.response.ResponseUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.enes.fitnes_api.dto.LoginDTO;
import com.enes.fitnes_api.dto.RegisterDTO;
import com.enes.fitnes_api.expectations.AllReadyExists;
import com.enes.fitnes_api.expectations.NotFoundExpection;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.UserRepository;

@Service
public class AuthServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtServices jwtServices;

    public ResponseUserDTO registerUser(RegisterDTO registerDto) {
        try {
            User user = new User();
            user.setEmail(registerDto.getEmail());
            user.setFullName(registerDto.getFullName());
            user.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
            User userFetched = userRepository.save(user);
            return new ResponseUserDTO(userFetched.getEmail(), userFetched.getFullName());
        } catch (Exception e) {
            throw new AllReadyExists("User Already Exists");
        }
    }

    public String login(LoginDTO loginDto) {
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
