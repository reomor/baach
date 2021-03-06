package com.reomor.core.repository;

import com.reomor.core.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ImageStorage {

    Image store(String directory, MultipartFile file);

    Image storeAndSave(String directory, MultipartFile file);

    Image load(String filename);

    List<Image> loadAll();
}
