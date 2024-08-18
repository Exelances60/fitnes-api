package com.enes.fitnes_api.controller;

import com.enes.fitnes_api.dto.SocialMediaReqDTO;
import com.enes.fitnes_api.handler.GenericResponse;
import com.enes.fitnes_api.services.SocialMediaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SocialMediaGraphController {

    @Autowired
    private SocialMediaServices socialMediaServices;


    @MutationMapping
    public GenericResponse<String> saveSocialMedia(@Argument SocialMediaReqDTO socialMedia) {
        socialMediaServices.saveSocialMedia(socialMedia);
        return GenericResponse.<String>builder().success(true).data("Social media account saved successfully").message("Social media account saved successfully").build();
    }
}
