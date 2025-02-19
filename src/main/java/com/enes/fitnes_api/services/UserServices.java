package com.enes.fitnes_api.services;

import com.enes.fitnes_api.dto.UpdateUserDTO;
import com.enes.fitnes_api.expectations.NotFoundExpection;
import com.enes.fitnes_api.mapper.UserConventor;
import com.enes.fitnes_api.model.Follow;
import com.enes.fitnes_api.model.User;
import com.enes.fitnes_api.repositroy.FollowRepository;
import com.enes.fitnes_api.repositroy.UserRepository;
import com.enes.fitnes_api.response.ResponseUserDetailsDTO;
import com.enes.fitnes_api.services.interfaces.IFirebaseServices;
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
    private IFirebaseServices firebaseServices;

    @Autowired
    private FollowRepository followRepository;

    public ResponseUserDetailsDTO getUserDetails(Long id) {
       User user = userRepository.findById(id).orElseThrow(() -> new NotFoundExpection("Kullanıcı bulunamadı"));
       user.setPostCount(user.getPosts().size());
       user.setFollowerCount(followRepository.countByFollowId(id));
       user.setFollowingCount(followRepository.countByUserId(id));
       User currentUser = getCurrentUser();
       if (currentUser != null && !currentUser.getId().equals(id)) {
           Follow follow = followRepository.findByUserIdAndFollowId(currentUser.getId(), id);
           user.setIsFollowed(follow != null);
       }
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

    public ResponseUserDetailsDTO saveUserBackgroundImage(MultipartFile file) {
       try {
           User userContext = getCurrentUser();
              User user = userRepository.findById(userContext.getId())
                     .orElseThrow(() -> new NotFoundExpection("Kullanıcı bulunamadı"));
           if (user.getBackgroundImage() != null && !user.getBackgroundImage().isEmpty()) {
               firebaseServices.delete(user.getBackgroundImage());
           }
           String imageUrl = firebaseServices.upload(file);
           user.setBackgroundImage(imageUrl);
           userRepository.save(user);
           return userConventor.convertToResponseUserDetailsDTO(user);
       } catch (IOException e) {
           throw new RuntimeException("Kullanıcı arka plan resmi yüklenirken hata oluştu", e);
       }
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
                            firebaseServices.delete(user.getImage());
                        }
                        String imageUrl = firebaseServices.upload((MultipartFile) value);
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

    public ResponseUserDetailsDTO followUser(Long followId) {
        User user = userRepository.findById(getCurrentUser().getId())
                .orElseThrow(() -> new NotFoundExpection("Kullanıcı bulunamadı"));
        User followUser = userRepository.findById(followId)
                .orElseThrow(() -> new NotFoundExpection("Takip edilecek kullanıcı bulunamadı"));
        Follow follow = followRepository.findByUserIdAndFollowId(getCurrentUser().getId(), followId);
        if (follow == null) {
            Follow newFollow = Follow.builder().userId(getCurrentUser().getId()).followId(followUser.getId()).user(user).follow(followUser).build();
            followRepository.save(newFollow);
            followUser.setIsFollowed(true);
        } else {
            followRepository.delete(follow);
            followUser.setIsFollowed(false);
        }
        userRepository.save(followUser);
        followUser.setFollowerCount(followRepository.countByFollowId(followId));
        followUser.setFollowingCount(followRepository.countByUserId(followId));
        return userConventor.convertToResponseUserDetailsDTO(followUser);
    }
}
