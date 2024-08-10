package com.enes.fitnes_api.response;

import com.enes.fitnes_api.model.SocialMediaAccount;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDetailsDTO {

    private String image;
    private Long id;
    private String fullName;
    private String email;
    private String job;
    private String phone;
    private Set<SocialMediaAccountDTO> socialMedia;
}
