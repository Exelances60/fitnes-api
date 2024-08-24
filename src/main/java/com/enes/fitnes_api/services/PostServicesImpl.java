package com.enes.fitnes_api.services;

import com.enes.fitnes_api.dto.CreatePostDTO;
import com.enes.fitnes_api.dto.PostDTO;
import com.enes.fitnes_api.expectations.NotFoundExpection;
import com.enes.fitnes_api.mapper.PostConvetor;
import com.enes.fitnes_api.model.Category;
import com.enes.fitnes_api.model.Post;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.PostRepository;
import com.enes.fitnes_api.response.ResponseHomePostDTO;
import com.enes.fitnes_api.services.interfaces.CategoryServices;
import com.enes.fitnes_api.services.interfaces.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServicesImpl implements PostServices {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserServices userService;

    @Autowired
    private CategoryServices categoryServices;

    @Autowired
    private PostConvetor postConvetor;

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
                .categoryId(createPostDTO.getCategoryId())
                .author(user)
                .build());
        return "Post saved successfully";
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostByCategory(Integer category) {
        return postRepository.findByCategoryId(category);
    }

    @Override
    public ResponseHomePostDTO getHomePosts() {
        List<Category> categories = categoryServices.getAll();
        List<Post> allPosts = postRepository
                .findAllByCategoryIdIn(categories.stream().map(Category::getId).collect(Collectors.toList()));

        List<Post> postProgram = allPosts.stream().filter(post -> post.getCategoryId() == 1)
                .collect(Collectors.toList());
        List<Post> postDiets = allPosts.stream().filter(post -> post.getCategoryId() == 2).collect(Collectors.toList());
        List<Post> postFood = allPosts.stream().filter(post -> post.getCategoryId() == 3).collect(Collectors.toList());
        List<Post> postScience = allPosts.stream().filter(post -> post.getCategoryId() == 4)
                .collect(Collectors.toList());
        List<Post> postSuggestions = allPosts.stream().filter(post -> post.getCategoryId() == 5)
                .collect(Collectors.toList());

        return ResponseHomePostDTO.builder()
                .postProgram(postConvetor.convertToPostDTOList(postProgram))
                .postDiets(postConvetor.convertToPostDTOList(postDiets))
                .postFood(postConvetor.convertToPostDTOList(postFood))
                .postScience(postConvetor.convertToPostDTOList(postScience))
                .postSuggestions(postConvetor.convertToPostDTOList(postSuggestions))
                .build();
    }

    @Override
    public PostDTO getPostById(Integer id) {
        return postConvetor.convertToPostDTO(
                postRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundExpection("Post Bulunamadı")));
    }

}
