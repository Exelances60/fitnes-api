package com.enes.fitnes_api.response;

import com.enes.fitnes_api.model.SocialMediaAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDetailsDTO {
    private Set<SocialMediaAccount> socialMediaAccounts;
}
