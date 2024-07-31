package com.enes.fitnes_api.repositroy;

import com.enes.fitnes_api.model.SocialMediaAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialMediaRepository extends JpaRepository<SocialMediaAccount, Long> {
}
