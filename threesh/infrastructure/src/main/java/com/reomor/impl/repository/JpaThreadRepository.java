package com.reomor.impl.repository;

import com.reomor.impl.entity.ThreadEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaThreadRepository extends CrudRepository<ThreadEntity, Long> {
    List<ThreadEntity> findAllByChannel_Id(Long channelId);
}
