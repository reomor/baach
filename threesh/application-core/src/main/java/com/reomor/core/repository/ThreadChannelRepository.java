package com.reomor.core.repository;

import com.reomor.core.domain.ThreadChannel;

import java.util.List;

public interface ThreadChannelRepository {

    ThreadChannel create(ThreadChannel channel);

    ThreadChannel get(Long id);

    ThreadChannel delete(ThreadChannel channel);

    ThreadChannel update(ThreadChannel channel);

    List<ThreadChannel> getAll();
}
