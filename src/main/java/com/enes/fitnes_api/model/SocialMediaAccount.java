package com.enes.fitnes_api.model;

import com.enes.fitnes_api.enums.SocialMediaPlatform;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Enumerated(EnumType.STRING)
    private SocialMediaPlatform platform;

    @Column(nullable = false)
    private String accountLink;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

}
