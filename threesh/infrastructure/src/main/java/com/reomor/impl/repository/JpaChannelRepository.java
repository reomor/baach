package com.reomor.impl.repository;

import com.reomor.impl.entity.ChannelEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaChannelRepository extends JpaRepository<ChannelEntity, Long> {

    List<ChannelEntity> findAll(Sort sort);

    //@EntityGraph(value = "channel.threads", type = EntityGraph.EntityGraphType.FETCH)
    @EntityGraph(attributePaths = {"threads"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT c FROM ChannelEntity c WHERE c.id=?1")
    Optional<ChannelEntity> findById(Long id);
}
