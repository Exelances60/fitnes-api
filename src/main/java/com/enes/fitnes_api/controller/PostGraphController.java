package com.enes.fitnes_api.controller;

import com.enes.fitnes_api.dto.CreatePostDTO;
import com.enes.fitnes_api.services.interfaces.PostServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PostGraphController {

    @Autowired
    private PostServices postService;

    @MutationMapping
    public String createPost(@Argument("input") @Valid CreatePostDTO createPostDTO) {
        return postService.createPost(createPostDTO);
    }

}
