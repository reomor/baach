package com.reomor.core.service;

import com.reomor.core.domain.Image;

public interface ImageService {

    Image create(Image image);

    Image get(Long id);

    Image delete(Image image);
}
