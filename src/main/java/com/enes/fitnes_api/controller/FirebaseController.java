package com.enes.fitnes_api.controller;

import com.enes.fitnes_api.handler.GenericResponse;
import com.enes.fitnes_api.response.ResponseUserDetailsDTO;
import com.enes.fitnes_api.services.UserServices;
import com.enes.fitnes_api.services.interfaces.IFirebaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/storage")
public class FirebaseController {

    @Autowired
    private IFirebaseServices firebaseService;

    @Autowired
    private UserServices userServices;


    @PostMapping("/upload-background")
    public ResponseEntity<GenericResponse<ResponseUserDetailsDTO>> uploadFile(
            @ModelAttribute MultipartFile file) {
        return ResponseEntity.ok(GenericResponse.<ResponseUserDetailsDTO>builder()
                .success(true)
                .data(userServices.saveUserBackgroundImage(file))
                .message("Dosya yüklendi")
                .build());
    }
}
