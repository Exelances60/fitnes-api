package com.enes.fitnes_api.repositroy;

import com.enes.fitnes_api.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByUserIdAndFollowId(Long id, Long followId);

    List<Follow> findByUserId(Long id);

    List<Follow> findByFollowId(Long id);

    Integer countByFollowId(Long id);

    Integer countByUserId(Long id);
}
