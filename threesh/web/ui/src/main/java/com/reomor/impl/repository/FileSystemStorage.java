package com.reomor.impl.repository;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileSystemStorage {

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Resource loadAsResource(String filename);

    void deleteAll();

}
