package com.reomor.impl.repository;

import com.reomor.impl.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaImageRepository extends JpaRepository<ImageEntity, Long> {
}
