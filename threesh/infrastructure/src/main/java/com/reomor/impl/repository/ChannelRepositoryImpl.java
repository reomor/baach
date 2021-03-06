package com.reomor.impl.repository;

import com.reomor.core.domain.Channel;
import com.reomor.core.repository.ChannelRepository;
import com.reomor.impl.entity.ChannelEntity;
import com.reomor.impl.mapper.DomainToEntityMapper;
import com.reomor.impl.mapper.EntityToDomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ChannelRepositoryImpl implements ChannelRepository {

    private final JpaChannelRepository channelRepository;
    private final DomainToEntityMapper domainToEntityMapper;
    private final EntityToDomainMapper entityToDomainMapper;

    @Autowired
    public ChannelRepositoryImpl(
            JpaChannelRepository channelRepository,
            DomainToEntityMapper domainToEntityMapper,
            EntityToDomainMapper entityToDomainMapper
    ) {
        this.channelRepository = channelRepository;
        this.domainToEntityMapper = domainToEntityMapper;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public Channel create(Channel channel) {
        ChannelEntity channelEntity = domainToEntityMapper.convertChannel(channel);
        return entityToDomainMapper.convertChannel(channelRepository.save(channelEntity), true);
    }

    @Override
    public Channel get(Long id) {
        return entityToDomainMapper.convertChannel(channelRepository.findById(id).orElse(null), true);
    }

    @Override
    public void delete(Channel channel) {
        channelRepository.deleteById(channel.getId());
    }

    @Override
    public Channel update(Channel channel) {
        return create(channel);
    }

    @Override
    public Channel getWithThreads(Long channelId) {
        ChannelEntity channelEntity = channelRepository.findById(channelId).orElse(null);
        return entityToDomainMapper.convertChannel(channelEntity, false);
    }


    @Override
    public List<Channel> getAll() {
        List<ChannelEntity> all = channelRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
        return all.stream()
                .map((ChannelEntity channelEntity) -> entityToDomainMapper.convertChannel(channelEntity, true))
                .collect(Collectors.toList());
    }
}
