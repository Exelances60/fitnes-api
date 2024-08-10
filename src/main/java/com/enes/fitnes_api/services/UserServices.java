package com.enes.fitnes_api.services;

import com.enes.fitnes_api.dto.UpdateUserDTO;
import com.enes.fitnes_api.expectations.NotFoundExpection;
import com.enes.fitnes_api.mapper.UserConventor;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.UserRepository;
import com.enes.fitnes_api.response.ResponseUserDetailsDTO;
import com.enes.fitnes_api.services.interfaces.IImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConventor userConventor;

    @Autowired
    private IImageUploadService imageUploadServices;

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

    public ResponseUserDetailsDTO updateUserDetails(Long id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExpection("Kullanıcı bulunamadı"));

        Class<?> dtoClass = updateUserDTO.getClass();
        Class<?> userClass = user.getClass();

        try {
            updateFields(dtoClass, userClass, updateUserDTO, user);
            userRepository.save(user);
        } catch (IllegalAccessException | IOException e) {
            throw new RuntimeException("Kullanıcı güncellenirken hata oluştu", e);
        }

        return userConventor.convertToResponseUserDetailsDTO(user);
    }

    private void updateFields(Class<?> dtoClass, Class<?> userClass, UpdateUserDTO dto, User user)
            throws IllegalAccessException, IOException {
        for (Field dtoField : dtoClass.getDeclaredFields()) {
            dtoField.setAccessible(true);
            Object value = dtoField.get(dto);
            if (value != null) {
                Field userField = getUserField(userClass, dtoField.getName());
                if (userField != null) {
                    userField.setAccessible(true);
                    if (dtoField.getName().equals("image") && value instanceof MultipartFile) {
                        if (user.getImage() != null && !user.getImage().isEmpty()) {
                            imageUploadServices.delete(user.getImage());
                        }
                        String imageUrl = imageUploadServices.upload((MultipartFile) value);
                        userField.set(user, imageUrl);
                    } else {
                        userField.set(user, value);
                    }
                }
            }

        }
    }

    private Field getUserField(Class<?> userClass, String fieldName) {
        try {
            return userClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Kullanıcı alanı bulunamadı: " + fieldName, e);
        }
    }
}
