package com.reomor.configuration;

import com.reomor.core.repository.ImageStorage;
import com.reomor.impl.repository.FileSystemStorage;
import com.reomor.impl.repository.FileSystemStorageRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    @ConditionalOnBean(name = "imageStorage")
    @ConditionalOnMissingBean
    public ImageStorage imageStorage(StorageProperties storageProperties, FileSystemStorage fileSystemStorage) {
        return new FileSystemStorageRepository(storageProperties, fileSystemStorage);
    }
}
