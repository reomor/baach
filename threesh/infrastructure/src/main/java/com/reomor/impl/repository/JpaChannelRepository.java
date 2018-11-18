package com.reomor.impl.repository;

import com.reomor.impl.entity.ChannelEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaChannelRepository extends CrudRepository<ChannelEntity, Long> {
    List<ChannelEntity> findAll();
}
