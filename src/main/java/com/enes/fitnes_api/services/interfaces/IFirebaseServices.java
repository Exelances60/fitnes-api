package com.enes.fitnes_api.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFirebaseServices {
    String upload(MultipartFile multipartFile) throws IOException;
    String delete(String fileUrl) throws IOException;
}
