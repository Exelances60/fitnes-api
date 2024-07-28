package com.enes.fitnes_api.services;

import com.enes.fitnes_api.expectations.NotFoundExpection;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.UserRepository;
import com.enes.fitnes_api.response.ResponseUserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;


    public ResponseUserDetailsDTO getUserDetails(Long id) {
        User user= userRepository.findById(id).orElseThrow(()->new NotFoundExpection("Kullanıcı bulunamadı"));
        return new ResponseUserDetailsDTO(user.getSocialMedia());
    }
}
