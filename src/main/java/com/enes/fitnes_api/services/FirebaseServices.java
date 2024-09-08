package com.enes.fitnes_api.services;

import com.enes.fitnes_api.services.interfaces.IFirebaseServices;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FirebaseServices implements IFirebaseServices {

    private final Storage storage;

    @Autowired
    public FirebaseServices(Storage storage) {
        this.storage = storage;
    }

    private File convertToFile(MultipartFile multipartFile, String fileName)  {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException("Error converting the multipart file to file", e);
        }
        return tempFile;
    }

    private String uploadFile(File file, String fileName, String contentType) throws IOException {
        BlobId blobId = BlobId.of("fitne-1e7ff.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/fitne-1e7ff.appspot.com/o/%s?alt=media";
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String upload(MultipartFile multipartFile){
        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            File file = convertToFile(multipartFile, fileName);
            String URL = uploadFile(file, fileName,multipartFile.getContentType());
            file.delete();
            return URL;
        } catch (IOException e) {
            throw new RuntimeException("Error uploading the file", e);
        }
    }

    public String delete(String fileUrl) throws IOException {
        String filePath = extractFilePathFromUrl(fileUrl);
        BlobId blobId = BlobId.of("fitne-1e7ff.appspot.com", filePath);
        boolean deleted = storage.delete(blobId);
        return deleted ? "Deleted" : "Error";
    }

    private String extractFilePathFromUrl(String fileUrl) {
        return fileUrl.replace("https://firebasestorage.googleapis.com/v0/b/fitne-1e7ff.appspot.com/o/", "")
                .replace("%2F", "/")
                .replace("?alt=media", "")
                .replaceAll("\\?", "");
    }
}
