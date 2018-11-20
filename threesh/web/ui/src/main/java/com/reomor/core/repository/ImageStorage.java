package com.reomor.core.repository;

import com.reomor.core.domain.Image;
import org.springframework.web.multipart.MultipartFile;


public interface ImageStorage {

    Image store(MultipartFile file);

    Image load(String filename);
}
