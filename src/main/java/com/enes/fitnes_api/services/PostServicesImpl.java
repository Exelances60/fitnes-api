package com.enes.fitnes_api.services;

import com.enes.fitnes_api.dto.CreatePostDTO;
import com.enes.fitnes_api.dto.CriteriaRequest;
import com.enes.fitnes_api.dto.Criterion;
import com.enes.fitnes_api.dto.PostDTO;
import com.enes.fitnes_api.expectations.NotFoundExpection;
import com.enes.fitnes_api.mapper.PostConvetor;
import com.enes.fitnes_api.model.Category;
import com.enes.fitnes_api.model.Post;
import com.enes.fitnes_api.model.PostLike;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.PostLikeRepository;
import com.enes.fitnes_api.repositroy.PostRepository;
import com.enes.fitnes_api.repositroy.PostSpecification;
import com.enes.fitnes_api.response.ResponseHomePostDTO;
import com.enes.fitnes_api.services.interfaces.CategoryServices;
import com.enes.fitnes_api.services.interfaces.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Override
    public String createPost(CreatePostDTO createPostDTO) {
        User user = userService.getCurrentUser();
        System.out.println(user.getFullName());
        if (user == null) {
            throw new NotFoundExpection("User not found");
        }
        postRepository.save(Post.builder()
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .image(createPostDTO.getImage() != null ? createPostDTO.getImage() : null)
                .categoryId(createPostDTO.getCategoryId())
                .authorId(user.getId())
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
        Post post = postRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundExpection("Post Bulunamadı"));
        post.setLiked(postLikeRepository.findByUserIdAndPostId(userService.getCurrentUser().getId(), Long.valueOf(id)) != null);
        return postConvetor.convertToPostDTO(post);
    }

    @Override
    public PostDTO likePost(Integer id) {
        User user = userService.getCurrentUser();
        Post post = postRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundExpection("Post not found"));
        PostLike postLike = postLikeRepository.findByUserIdAndPostId(user.getId(), Long.valueOf(id));
        if (postLike != null) {
            postLikeRepository.delete(postLike);
            post.getLikes();
            post.setLiked(false);
            return postConvetor.convertToPostDTO(post);
        }
        postLikeRepository.save(PostLike.builder().postId(post.getId()).userId(user.getId()).build());
        post.getLikes();
        post.setLiked(true);
        return postConvetor.convertToPostDTO(post);
    }

        @Override
        public List<PostDTO> getAllPostsByCriteria(CriteriaRequest criteriaRequest) {
            Pageable pageable = PageRequest.of(criteriaRequest.getPage(), criteriaRequest.getSize());
            var specification = getSpecificationFromRequest(criteriaRequest.getCriteria());
            Page<Post> posts = postRepository.findAll(specification, pageable);
            List<Post> postList = posts.getContent();
            return postConvetor.convertToPostDTOList(postList.stream().map(post -> {
                post.setLiked(postLikeRepository.findByUserIdAndPostId(userService.getCurrentUser().getId(), post.getId()) != null);
                return post;
            }).collect(Collectors.toList()));
        }

    @Override
    public String deletePost(int postIdInt) {
        User user = userService.getCurrentUser();
        Post post = postRepository.findById(Long.valueOf(postIdInt)).orElseThrow(() -> new NotFoundExpection("Post not found"));
        if (post.getAuthorId() != user.getId()) {
            throw new NotFoundExpection("You are not authorized to delete this post");
        }
        postRepository.delete(post);
        return "Post Başarıyla Silindi";
    }

    private Specification<Post> getSpecificationFromRequest(List<Criterion> criteria) {
            Specification<Post> specification = Specification.where(null);

            for (Criterion criterion : criteria) {
                specification = specification.and(new PostSpecification(criterion));
            }
            return specification;
        }
}
