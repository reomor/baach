package com.reomor.impl.repository;

import com.reomor.configuration.StorageProperties;
import com.reomor.core.domain.Image;
import com.reomor.core.repository.ImageStorage;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageStorageImpl implements ImageStorage {

    private final Path rootLocation;
    private final FileSystemStorage fileSystemStorage;

    public ImageStorageImpl(StorageProperties properties, FileSystemStorage fileSystemStorage) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.fileSystemStorage = fileSystemStorage;
    }

    @Override
    public Image store(MultipartFile file) {
        return null;
    }

    @Override
    public Image load(String filename) {
        return null;
    }
}
