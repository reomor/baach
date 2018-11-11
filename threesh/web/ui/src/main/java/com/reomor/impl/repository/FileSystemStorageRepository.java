package com.reomor.impl.repository;

import com.reomor.configuration.StorageProperties;
import com.reomor.core.domain.Image;
import com.reomor.core.repository.ImageStorage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemStorageRepository implements ImageStorage {

    private final Path rootLocation;
    private final FileSystemStorage fileSystemStorage;

    public FileSystemStorageRepository(StorageProperties properties, FileSystemStorage fileSystemStorage) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.fileSystemStorage = fileSystemStorage;
    }

    @Override
    public Image store(Image image) {
        return null;
    }

    @Override
    public Image load(String filename) {
        return null;
    }
}
