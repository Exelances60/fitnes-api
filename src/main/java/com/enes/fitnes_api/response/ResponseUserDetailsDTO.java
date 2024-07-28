package com.enes.fitnes_api.response;

import com.enes.fitnes_api.model.SocialMediaAccount;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDetailsDTO {
    private Long id;
    private String fullName;
    private String email;
    private Set<SocialMediaAccountDTO> socialMedia;
}
