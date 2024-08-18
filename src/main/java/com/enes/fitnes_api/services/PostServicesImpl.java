package com.enes.fitnes_api.services;

import com.enes.fitnes_api.dto.CreatePostDTO;
import com.enes.fitnes_api.expectations.NotFoundExpection;
import com.enes.fitnes_api.model.Post;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.PostRepository;
import com.enes.fitnes_api.services.interfaces.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServicesImpl implements PostServices {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserServices userService;

    @Override
    public String createPost(CreatePostDTO createPostDTO) {
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new NotFoundExpection("User not found");
        }
        postRepository.save(Post.builder()
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .image(createPostDTO.getImage() != null ? createPostDTO.getImage() : null)
                .author(user)
                .build());
        return "Post saved successfully";

    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
