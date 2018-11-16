package com.reomor.core.service;

import com.reomor.core.domain.Image;

public interface ImageService {

    Image store(Image image);

    Image load(String filename);
}
