package com.reomor.impl.repository;

import com.reomor.core.domain.Image;
import com.reomor.core.repository.ImageStorage;
import com.reomor.impl.entity.ImageEntity;
import com.reomor.impl.mapper.DomainToEntityMapper;
import com.reomor.impl.mapper.EntityToDomainMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;


@Repository
public class ImageStorageImpl implements ImageStorage {

    private final FileSystemStorage fileSystemStorage;
    private final JpaImageRepository jpaImageRepository;
    private final DomainToEntityMapper domainToEntityMapper;
    private final EntityToDomainMapper entityToDomainMapper;

    public ImageStorageImpl(
            FileSystemStorage fileSystemStorage,
            JpaImageRepository jpaImageRepository,
            DomainToEntityMapper domainToEntityMapper,
            EntityToDomainMapper entityToDomainMapper
    ) {
        this.fileSystemStorage = fileSystemStorage;
        this.jpaImageRepository = jpaImageRepository;
        this.domainToEntityMapper = domainToEntityMapper;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public Image store(String directory, MultipartFile file) {
        return fileSystemStorage.store(directory, file);
    }

    @Override
    public Image storeAndSave(String directory, MultipartFile file) {
        Image storedImage = fileSystemStorage.store(directory, file);
        ImageEntity imageEntity = jpaImageRepository.save(domainToEntityMapper.convertImage(storedImage));
        return entityToDomainMapper.convertImage(imageEntity);
    }

    @Override
    public Image load(String filename) {
        return null;
    }
}
