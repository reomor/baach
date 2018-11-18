package com.reomor.impl.repository;

import com.reomor.impl.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaPostRepository extends CrudRepository<PostEntity, Long> {
    List<PostEntity> findAllByThread_Id(Long threadId);
}
