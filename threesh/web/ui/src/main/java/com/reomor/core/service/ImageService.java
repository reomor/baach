package com.reomor.core.service;

import com.reomor.core.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    Image store(String directory, MultipartFile file);

    Image storeAndSave(String directory, MultipartFile file);

    Image load(String filename);

    List<Image> loadAll();
}
