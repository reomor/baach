package com.reomor.impl.mapper;

import com.reomor.core.domain.*;
import com.reomor.core.domain.Thread;
import com.reomor.impl.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DomainToEntityMapper {

    public UserEntity convertUser(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getPasswordHash(), user.getPasswordSalt(), user.getRolesList());
    }

    public ImageEntity convertImage(Image image) {
        return new ImageEntity(image.getId(), image.getDirectory(), image.getFileName());
    }

    public PostEntity convertPost(Post post) {
        ImageEntity imageEntity = convertImage(post.getImages());
        return new PostEntity(post.getId(), post.getMessage(), imageEntity, post.getDateTime());
    }

    public ThreadEntity convertThread(Thread thread) {
        ImageEntity imageEntity = convertImage(thread.getImage());
        List<PostEntity> postEntities = thread.getPosts().stream().map(this::convertPost).collect(Collectors.toList());
        return new ThreadEntity(thread.getId(), thread.getMessage(), thread.getDateTime(), imageEntity, postEntities);
    }

    public ChannelEntity convertChannel(Channel channel) {
        List<ThreadEntity> threadEntities = channel.getThreads().stream().map(this::convertThread).collect(Collectors.toList());
        return new ChannelEntity(channel.getId(), channel.getName(), threadEntities);
    }
}
