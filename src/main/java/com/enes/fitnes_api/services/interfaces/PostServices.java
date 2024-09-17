package com.enes.fitnes_api.services.interfaces;

import com.enes.fitnes_api.dto.CreatePostDTO;
import com.enes.fitnes_api.dto.CriteriaRequest;
import com.enes.fitnes_api.dto.PostDTO;
import com.enes.fitnes_api.model.Post;
import com.enes.fitnes_api.response.ResponseHomePostDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PostServices {
    String createPost(CreatePostDTO createPostDTO);

    List<Post> getAllPosts();

    List<Post> getPostByCategory(Integer category);

    ResponseHomePostDTO getHomePosts();

    PostDTO getPostById(Integer id);

    PostDTO likePost(Integer id);

    List<PostDTO> getAllPostsByCriteria(CriteriaRequest criteriaRequest);

    String deletePost(int postIdInt);
}
