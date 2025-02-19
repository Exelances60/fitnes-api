package com.enes.fitnes_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostDTO {
    private Integer id;
    private String title;
    private String content;
    private String image;
    private Integer categoryId;
    private Integer likes;
    private boolean isLiked = false;
    private AuthorDTO author;
    private Date createdAt;
}
