package com.enes.fitnes_api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.enes.fitnes_api.dto.LoginDTO;
import com.enes.fitnes_api.dto.RegisterDTO;
import com.enes.fitnes_api.response.ResponseUserDTO;
import com.enes.fitnes_api.handler.GenericResponse;
import com.enes.fitnes_api.services.AuthServices;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/auth")
@RestController
public class AuthRestController {

    @Autowired
    private AuthServices userServices;

    @PostMapping("register")
    public ResponseEntity<GenericResponse<ResponseUserDTO>> postMethodName(
            @Valid @RequestBody RegisterDTO registerDto) {
        return ResponseEntity
                .ok(GenericResponse.<ResponseUserDTO>builder().success(true)
                        .data(userServices.registerUser(registerDto))
                        .message("Account created Succes").build());
    }

    @PostMapping("login")
    public ResponseEntity<GenericResponse<String>> login(@Valid @RequestBody LoginDTO loginDto) {
        return ResponseEntity.ok(GenericResponse.<String>builder().success(true).data(userServices.login(loginDto))
                .message("Login Succes").build());
    }

}
