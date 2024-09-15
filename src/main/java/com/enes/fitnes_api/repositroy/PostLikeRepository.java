package com.enes.fitnes_api.repositroy;

import com.enes.fitnes_api.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {

    PostLike findByUserIdAndPostId(Long userId, Long postId);

}
