package com.enes.fitnes_api.response;

import com.enes.fitnes_api.enums.SocialMediaPlatform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaAccountDTO {
    private Long id;
    private SocialMediaPlatform platform;
    private String accountLink;

}
