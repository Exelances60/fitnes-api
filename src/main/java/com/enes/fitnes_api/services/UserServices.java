package com.enes.fitnes_api.services;

import com.enes.fitnes_api.expectations.NotFoundExpection;
import com.enes.fitnes_api.mapper.UserConventor;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.UserRepository;
import com.enes.fitnes_api.response.ResponseUserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConventor userConventor;

    public ResponseUserDetailsDTO getUserDetails(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundExpection("Kullanıcı bulunamadı"));
        return userConventor.convertToResponseUserDetailsDTO(user);
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}
