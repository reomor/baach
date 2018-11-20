package com.reomor.impl.service;

import com.reomor.core.domain.Image;
import com.reomor.core.repository.ImageStorage;
import com.reomor.core.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageStorage imageStorage;

    public ImageServiceImpl(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }

    @Override
    public Image store(String directory, MultipartFile file) {
        return imageStorage.store(directory, file);
    }

    @Override
    public Image load(String filename) {
        return null;
    }
}
