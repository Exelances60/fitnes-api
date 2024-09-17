package com.enes.fitnes_api.controller;

import com.enes.fitnes_api.dto.CriteriaRequest;
import com.enes.fitnes_api.dto.PostDTO;
import com.enes.fitnes_api.handler.GenericResponse;
import com.enes.fitnes_api.model.Post;
import com.enes.fitnes_api.response.ResponseHomePostDTO;
import com.enes.fitnes_api.services.interfaces.PostServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostRestController {

    @Autowired
    private PostServices postServices;

    @GetMapping("/home-posts")
    public ResponseEntity<GenericResponse<ResponseHomePostDTO>> getHomePosts() {
        return ResponseEntity.ok(GenericResponse.<ResponseHomePostDTO>builder().success(true).data(postServices.getHomePosts()).message("Posts fetched successfully").build());
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<Post>>> getPostByCategory(@RequestParam("category") Integer category) {
        return ResponseEntity.ok(GenericResponse.<List<Post>>builder().success(true).data(postServices.getPostByCategory(category)).message("Posts fetched successfully").build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<PostDTO>> getPostById(@PathVariable String id) {
        int postId = Integer.parseInt(id);
        if (postId < 1) {
            throw new IllegalArgumentException("Invalid id: must be greater than 0");
        }
        return ResponseEntity.ok(GenericResponse.<PostDTO>builder().success(true).data(postServices.getPostById(postId)).message("Post fetched successfully").build());
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<GenericResponse<PostDTO>> likePost(@PathVariable String postId) {
        int postIdInt = Integer.parseInt(postId);
        if (postIdInt < 1) {
            throw new IllegalArgumentException("Invalid id: must be greater than 0");
        }
        return ResponseEntity.ok(GenericResponse.<PostDTO>builder().success(true).data(postServices.likePost(postIdInt)).message("Post liked successfully").build());
    }

    @PostMapping("/all-posts")
    public ResponseEntity<GenericResponse<List<PostDTO>>> getAllPosts(@RequestBody @Valid CriteriaRequest criteriaRequest) {
        return ResponseEntity.ok(GenericResponse.<List<PostDTO>>builder().success(true).data(postServices.getAllPostsByCriteria(criteriaRequest)).message("Posts fetched successfully").build());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<GenericResponse<String>> deletePost(@PathVariable String postId) {
        int postIdInt = Integer.parseInt(postId);
        if (postIdInt < 1) {
            throw new IllegalArgumentException("Invalid id: must be greater than 0");
        }
        return ResponseEntity.ok(GenericResponse.<String>builder().success(true).data(postServices.deletePost(postIdInt)).message("Post deleted successfully").build());
    }

}
