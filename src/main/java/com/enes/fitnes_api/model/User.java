package com.enes.fitnes_api.model;

import java.sql.Date;
import java.util.Collection;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

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

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<SocialMediaAccount> socialMedia = new HashSet<>();

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
