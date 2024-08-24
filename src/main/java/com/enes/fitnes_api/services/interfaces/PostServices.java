package com.enes.fitnes_api.services.interfaces;

import com.enes.fitnes_api.dto.CreatePostDTO;
import com.enes.fitnes_api.dto.PostDTO;
import com.enes.fitnes_api.model.Post;
import com.enes.fitnes_api.response.ResponseHomePostDTO;

import java.util.List;

public interface PostServices {
    String createPost(CreatePostDTO createPostDTO);

    List<Post> getAllPosts();

    List<Post> getPostByCategory(Integer category);

    ResponseHomePostDTO getHomePosts();

    PostDTO getPostById(Integer id);
}
