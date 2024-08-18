package com.enes.fitnes_api.controller;

import com.enes.fitnes_api.dto.CreatePostDTO;
import com.enes.fitnes_api.handler.GenericResponse;
import com.enes.fitnes_api.model.Post;
import com.enes.fitnes_api.services.interfaces.PostServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostGraphController {

    @Autowired
    private PostServices postService;

    @MutationMapping
    public String createPost(@Argument("input") @Valid CreatePostDTO createPostDTO) {
        return postService.createPost(createPostDTO);
    }

    @QueryMapping
    public GenericResponse<List<Post>> getAllPosts() {
        return GenericResponse.<List<Post>>builder().success(true).data(postService.getAllPosts()).message("All posts fetched successfully").build();
    }

}
