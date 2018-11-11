package com.reomor.core.service;

import com.reomor.core.domain.Image;

import java.util.List;

public interface ImageStorageService {

    Image store(Image image);

    Image load(String filename);
}
