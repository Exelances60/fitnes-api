package com.enes.fitnes_api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.enes.fitnes_api.dto.LoginDto;
import com.enes.fitnes_api.dto.RegisterDto;
import com.enes.fitnes_api.handler.GenericResponse;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthRestController {

    @Autowired
    private UserServices userServices;

    @PostMapping("register")
    public ResponseEntity<GenericResponse<User>> postMethodName(@RequestBody RegisterDto registerDto) {
        return ResponseEntity
                .ok(GenericResponse.<User>builder().success(true).data(userServices.registerUser(registerDto))
                        .message("Account created Succes").build());
    }

    @PostMapping("login")
    public ResponseEntity<GenericResponse<String>> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(GenericResponse.<String>builder().success(true).data(userServices.login(loginDto))
                .message("Login Succes").build());
    }

}
