package com.reomor.impl.repository;

import com.reomor.impl.entity.ChannelEntity;
import org.springframework.data.repository.CrudRepository;

public interface JpaChannelRepository extends CrudRepository<ChannelEntity, Long> {
}
