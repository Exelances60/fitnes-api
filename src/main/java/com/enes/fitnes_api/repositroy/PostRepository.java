package com.enes.fitnes_api.repositroy;

import com.enes.fitnes_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(Integer categoryId);

    List<Post> findAllByCategoryIdIn(List<Integer> categoryIds);
}
