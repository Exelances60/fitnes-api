package com.enes.fitnes_api.services.interfaces;

import com.enes.fitnes_api.dto.CreatePostDTO;
import com.enes.fitnes_api.model.Post;

import java.util.List;

public interface PostServices {
    String createPost(CreatePostDTO createPostDTO);

    List<Post> getAllPosts();
}
