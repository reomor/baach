package com.reomor.core.repository;

import com.reomor.core.domain.Image;


public interface ImageStorage {

    Image store(Image image);

    Image load(String filename);
}
