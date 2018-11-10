package com.reomor.core.service;

import com.reomor.core.domain.Image;

public interface ImageStorageService {

    Image store(Image image);

    Image load(String filename);

    Image delete(Image image);
}
