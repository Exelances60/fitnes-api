package com.enes.fitnes_api.repositroy;

import com.enes.fitnes_api.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    List<Post> findByCategoryId(Integer categoryId);

    List<Post> findAllByCategoryIdIn(List<Integer> categoryIds);

    @Override
    Page<Post> findAll(Pageable pageable);

}
