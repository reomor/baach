package com.reomor.impl.service;

import com.reomor.core.domain.Channel;
import com.reomor.core.repository.ChannelRepository;
import com.reomor.core.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelServiceImpl(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public Channel create(Channel channel) {
        return channelRepository.create(channel);
    }

    @Override
    public Channel get(Long id) {
        return channelRepository.get(id);
    }

    @Override
    public void delete(Channel channel) {
        channelRepository.delete(channel);
    }

    @Override
    public Channel update(Channel channel) {
        return channelRepository.update(channel);
    }

    @Override
    public List<Channel> getAll() {
        return channelRepository.getAll();
    }
}
