package com.reomor.impl.service;

import com.reomor.core.domain.Image;
import com.reomor.core.repository.ImageStorage;
import com.reomor.core.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public Image storeAndSave(String directory, MultipartFile file) {
        return imageStorage.storeAndSave(directory, file);
    }

    @Override
    public Image load(String filename) {
        return null;
    }

    @Override
    public List<Image> loadAll() {
        return imageStorage.loadAll();
    }
}
