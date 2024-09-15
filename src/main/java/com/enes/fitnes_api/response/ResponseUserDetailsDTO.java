package com.enes.fitnes_api.response;

import com.enes.fitnes_api.model.Follow;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDetailsDTO {

    private String image;
    private String backgroundImage;
    private Long id;
    private String fullName;
    private String email;
    private String job;
    private String phone;
    private Set<SocialMediaAccountDTO> socialMedia;
    private String summary;
    private String address;
    private Integer postCount;
    private Integer followingCount;
    private Integer followerCount;
    private Boolean isFollowed = false;

    private Date createdAt;
}
