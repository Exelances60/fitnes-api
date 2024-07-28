package com.enes.fitnes_api.model;

import com.enes.fitnes_api.enums.SocialMediaPlatform;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "social_media_accounts")
public class SocialMediaAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private SocialMediaPlatform platform;

    @Column(nullable = false)
    private String accountLink;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
