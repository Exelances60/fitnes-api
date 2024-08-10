package com.enes.fitnes_api.controller;

import com.enes.fitnes_api.dto.UpdateUserDTO;
import com.enes.fitnes_api.handler.GenericResponse;
import com.enes.fitnes_api.response.ResponseUserDetailsDTO;
import com.enes.fitnes_api.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@Controller
public class UserRestController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/user-details")
    public ResponseEntity<GenericResponse<ResponseUserDetailsDTO>> getUserInfo(@RequestParam Long id) {
        return ResponseEntity.ok(GenericResponse.<ResponseUserDetailsDTO>builder().success(true).data(userServices.getUserDetails(id)).message("User Info").build());
    }

    @PutMapping("/update-user")
    public ResponseEntity<GenericResponse<ResponseUserDetailsDTO>> updateUserInfo(@RequestParam Long id, @ModelAttribute UpdateUserDTO updateUserDTO) {
        return ResponseEntity.ok(GenericResponse.<ResponseUserDetailsDTO>builder()
                .success(true)
                .data(userServices.updateUserDetails(id, updateUserDTO))
                .message("Kullanıcı bilgileri güncellendi")
                .build());
    }
}
    
