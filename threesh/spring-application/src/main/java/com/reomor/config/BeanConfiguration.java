package com.reomor.config;

import com.reomor.configuration.StorageProperties;
import com.reomor.core.repository.ImageStorage;
import com.reomor.core.service.ThreadService;
import com.reomor.impl.repository.FileSystemStorage;
import com.reomor.impl.repository.ImageStorageImpl;
import com.reomor.impl.service.ThreadServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ImageStorage imageStorage(StorageProperties storageProperties, FileSystemStorage fileSystemStorage) {
        return new ImageStorageImpl(storageProperties, fileSystemStorage);
    }

    @Bean
    public ThreadService postThreadService(ImageStorage imageStorage) {
        return new ThreadServiceImpl(imageStorage);
    }
}
