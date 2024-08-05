package com.enes.fitnes_api.repositroy;

import com.enes.fitnes_api.enums.SocialMediaPlatform;
import com.enes.fitnes_api.model.SocialMediaAccount;
import com.enes.fitnes_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialMediaRepository extends JpaRepository<SocialMediaAccount, Long> {

    boolean existsByUserAndPlatform(User user, SocialMediaPlatform platform);

    SocialMediaAccount findByUserAndPlatform(User user, SocialMediaPlatform platform);

    void deleteByUserAndId(User user, Long id);
}
