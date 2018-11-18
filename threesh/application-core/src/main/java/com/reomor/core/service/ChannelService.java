package com.reomor.core.service;

import com.reomor.core.domain.Channel;

import java.util.List;

public interface ChannelService {

    Channel create(Channel channel);

    Channel get(Long id);

    void delete(Channel channel);

    Channel update(Channel channel);

    List<Channel> getAll();
}
