package com.enes.fitnes_api.services;

import com.enes.fitnes_api.model.SocialMediaAccount;
import com.enes.fitnes_api.repositroy.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialMediaServices {

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    public void saveSocialMedia(SocialMediaAccount socialMedia) {
        socialMediaRepository.save(socialMedia);

    }

}
