package com.reomor.config;

import com.reomor.configuration.StorageProperties;
import com.reomor.core.repository.ImageStorage;
import com.reomor.core.service.PostThreadService;
import com.reomor.impl.repository.FileSystemStorage;
import com.reomor.impl.repository.FileSystemStorageRepository;
import com.reomor.impl.service.PostThreadServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    //@ConditionalOnBean(name = "imageStorage")
    //@ConditionalOnMissingBean
    public ImageStorage imageStorage(StorageProperties storageProperties, FileSystemStorage fileSystemStorage) {
        return new FileSystemStorageRepository(storageProperties, fileSystemStorage);
    }

    @Bean
    public PostThreadService postThreadService(ImageStorage imageStorage) {
        return new PostThreadServiceImpl(imageStorage);
    }
}
