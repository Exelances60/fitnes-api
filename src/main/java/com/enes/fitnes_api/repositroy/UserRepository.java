package com.enes.fitnes_api.repositroy;

import org.springframework.stereotype.Repository;

import com.enes.fitnes_api.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
