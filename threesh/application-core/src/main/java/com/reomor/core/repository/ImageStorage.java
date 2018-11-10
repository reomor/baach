package com.reomor.core.repository;

import com.reomor.core.domain.Image;

public interface ImageStorage {

    Image create(Image image);

    Image get(Long id);

    Image delete(Image image);
}
