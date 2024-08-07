package com.enes.fitnes_api.controller;


import com.enes.fitnes_api.dto.SocialMediaReqDTO;
import com.enes.fitnes_api.handler.GenericResponse;
import com.enes.fitnes_api.services.SocialMediaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social-media")
public class SocialMediaController {

    @Autowired
    private SocialMediaServices socialMediaServices;

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<String>> saveSocialMedia(@RequestBody SocialMediaReqDTO socialMedia) {
        socialMediaServices.saveSocialMedia(socialMedia);
        return ResponseEntity.ok(GenericResponse.<String>builder().success(true).data("Social media account saved successfully").message("Social media account saved successfully").build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse<String>> deleteSocialMedia(@PathVariable Long id) {
        socialMediaServices.deleteSocialMedia(id);
        return ResponseEntity.ok(GenericResponse.<String>builder().success(true).data("Social media account deleted successfully").message("Social media account deleted successfully").build());
    }

}
