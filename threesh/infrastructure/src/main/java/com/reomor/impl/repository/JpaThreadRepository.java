package com.reomor.impl.repository;

import com.reomor.impl.entity.ThreadEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaThreadRepository extends JpaRepository<ThreadEntity, Long> {
    List<ThreadEntity> findAllByChannel_Id(Long channelId, Sort sort);
}
