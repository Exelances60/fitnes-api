package com.enes.fitnes_api.mapper;

import com.enes.fitnes_api.model.SocialMediaAccount;
import com.enes.fitnes_api.response.SocialMediaAccountDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocialMediaMapper {

    SocialMediaAccountDTO toSocialMediaAccountDTO(SocialMediaAccount socialMediaAccount);
}
