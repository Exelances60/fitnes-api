package com.enes.fitnes_api.controller;

import com.enes.fitnes_api.handler.GenericResponse;
import com.enes.fitnes_api.response.ResponseUserDetailsDTO;
import com.enes.fitnes_api.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/user")
@Controller
public class UserRestController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/user-details")
    public ResponseEntity<GenericResponse<ResponseUserDetailsDTO>> getUserInfo(@RequestParam Long id) {
        return ResponseEntity.ok(GenericResponse.<ResponseUserDetailsDTO>builder().success(true).data(userServices.getUserDetails(id)).message("User Info").build());
    }
}
