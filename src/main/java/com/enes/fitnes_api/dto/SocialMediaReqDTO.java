package com.enes.fitnes_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class SocialMediaReqDTO {
    private Map<String, String> socialMediaAccounts;
}
