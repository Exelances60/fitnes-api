package com.enes.fitnes_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enes.fitnes_api.expectations.NotFoundExpection;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.UserRepository;

@Service
public class UserDetailsServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundExpection("User not found with email: "));
        return user;
    }

}
