package com.enes.fitnes_api.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class StorageConfig {

    @Bean
    public Storage storage() throws IOException {
        InputStream inputStream = StorageConfig.class.getClassLoader().getResourceAsStream("fitne-1e7ff-firebase-adminsdk-rdlsj-90848e6da7.json");
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
