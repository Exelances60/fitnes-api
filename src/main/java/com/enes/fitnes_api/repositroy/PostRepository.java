package com.enes.fitnes_api.repositroy;

import com.enes.fitnes_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
