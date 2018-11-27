package com.reomor.impl.mapper;

import com.reomor.core.domain.*;
import com.reomor.core.domain.Thread;
import com.reomor.impl.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DomainToEntityMapper {

    public VerificationTokenEntity convertToken(VerificationToken token) {
        return new VerificationTokenEntity(token.getId(), token.getToken(), null, token.getExpiryDate());
    }

    public UserEntity convertUser(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getPasswordHash(), user.getPasswordSalt(), user.isEnabled(), user.getRolesList());
    }

    public ImageEntity convertImage(Image image) {
        return new ImageEntity(image.getId(), image.getDirectory(), image.getFileName());
    }

    public PostEntity convertPost(Post post) {
        return new PostEntity(post.getId(), null, post.getMessage(), null, post.getDateTime());
    }

    public ThreadEntity convertThread(Thread thread) {
        ImageEntity imageEntity = null;
        if (thread.getImage() != null) {
            imageEntity = convertImage(thread.getImage());
        }
        List<PostEntity> postEntities = null;
        if (!CollectionUtils.isEmpty(thread.getPosts())) {
             postEntities = thread.getPosts().stream().map(this::convertPost).collect(Collectors.toList());
        }
        return new ThreadEntity(thread.getId(), thread.getMessage(), thread.getDateTime(), imageEntity, postEntities);
    }

    public ChannelEntity convertChannel(Channel channel) {
        List<ThreadEntity> threadEntities = channel.getThreads().stream().map(this::convertThread).collect(Collectors.toList());
        return new ChannelEntity(channel.getId(), channel.getName(), channel.getDescription(), channel.getRules(), threadEntities);
    }
}
