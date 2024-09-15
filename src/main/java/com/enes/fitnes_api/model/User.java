    package com.enes.fitnes_api.model;

    import java.sql.Date;
    import java.util.*;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import lombok.Getter;
    import lombok.Setter;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;

    import org.hibernate.validator.constraints.Length;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;


    @Entity
    @Getter
    @Setter
    @Table(name = "users")
    public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false)
        private Long id;

        @Column()
        private String image;

        @Column()
        private String backgroundImage;

        @Column(nullable = false)
        @NotBlank(message = "Isim bos olamaz")
        @Length(min = 3, message = "Isim en az 3 karakter olmali")
        private String fullName;

        @Column(unique = true, nullable = false, length = 100)
        @NotBlank(message = "Email bos olamaz")
        @Email(message = "Email formati yanlis")
        private String email;

        @Column(nullable = false)
        @NotBlank(message = "Sifre bos olamaz")
        private String password;

        @Column()
        private String job;

        @Column()
        @Length(min = 10, message = "Telefon numarasi en az 10 karakter olmali")
        private String phone;

        @Column()
        private String address;

        @Column(columnDefinition = "TEXT")
        private String summary;

        @Transient
        private Integer followerCount;

        @Transient
        private Integer followingCount;

        @Transient
        private Boolean isFollowed = false;

        @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
        private List<Post> posts = new ArrayList<>();

        @Transient
        private Integer postCount;

        @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
        @JsonIgnore
        private Set<SocialMediaAccount> socialMedia;

        @CreationTimestamp
        @Column(nullable = false, name = "created_at")
        private Date createdAt;

        @UpdateTimestamp
        @Column(name = "updated_at")
        private Date updatedAt;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority("user"));
        }

        public String getPasswordString() {
            return password;
        }

        public String getIdString() {
            return id.toString();
        }

        @Override
        public String getUsername() {
            return email;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }


    }
