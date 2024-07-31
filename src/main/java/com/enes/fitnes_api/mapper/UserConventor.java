package com.enes.fitnes_api.mapper;

import com.enes.fitnes_api.model.SocialMediaAccount;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.response.ResponseUserDetailsDTO;
import com.enes.fitnes_api.response.SocialMediaAccountDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserConventor {
    ResponseUserDetailsDTO convertToResponseUserDetailsDTO(User user);
    Set<SocialMediaAccountDTO> convertToSocialMediaAccountDTOSet(Set<SocialMediaAccount> socialMediaAccounts);
    SocialMediaAccountDTO convertToSocialMediaAccountDTO(SocialMediaAccount socialMediaAccount);
}
