package com.reomor.impl.repository;

import com.reomor.impl.entity.ThreadEntity;
import org.springframework.data.repository.CrudRepository;

public interface JpaThreadRepository extends CrudRepository<ThreadEntity, Long> {
}
