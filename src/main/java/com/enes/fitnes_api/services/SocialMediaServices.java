package com.enes.fitnes_api.services;

import com.enes.fitnes_api.dto.SocialMediaReqDTO;
import com.enes.fitnes_api.enums.SocialMediaPlatform;
import com.enes.fitnes_api.model.SocialMediaAccount;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialMediaServices {

    @Autowired
    private SocialMediaRepository socialMediaRepository;

    @Autowired
    private UserServices userServices;


    public String saveSocialMedia(SocialMediaReqDTO socialMediaDto) {
        Map<String, String> socialMediaAccounts = socialMediaDto.getSocialMediaAccounts();
        User user = userServices.getCurrentUser();
        socialMediaAccounts.entrySet().stream()
                .filter(entry -> hasUrl(entry.getValue()))
                .map(entry -> createSocialMediaAccount(entry, user))
                .forEach(account-> {
                    if (socialMediaRepository.existsByUserAndPlatform(user, account.getPlatform())) {
                        SocialMediaAccount socialMediaAccount = socialMediaRepository.findByUserAndPlatform(user, account.getPlatform());
                        socialMediaAccount.setAccountLink(account.getAccountLink());
                        socialMediaRepository.save(socialMediaAccount);
                    } else {
                        socialMediaRepository.save(account);
                    }});


        return "Social media account saved successfully";
    }

    private boolean hasUrl(String url) {
        return url != null && !url.isEmpty();
    }

    private SocialMediaAccount createSocialMediaAccount(Map.Entry<String, String> entry, User user) {
        SocialMediaAccount socialMediaAccount = new SocialMediaAccount();
        socialMediaAccount.setPlatform(SocialMediaPlatform.valueOf(entry.getKey()));
        socialMediaAccount.setAccountLink(entry.getValue());
        socialMediaAccount.setUser(user);
        return socialMediaAccount;
    }

}
