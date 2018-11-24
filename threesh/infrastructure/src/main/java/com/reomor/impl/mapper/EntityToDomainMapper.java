package com.reomor.impl.mapper;

import com.reomor.core.domain.*;
import com.reomor.core.domain.Thread;
import com.reomor.impl.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityToDomainMapper {

    public VerificationToken convertToken(VerificationTokenEntity tokenEntity) {
        User user = convertUser(tokenEntity.getUser());
        return new VerificationToken(tokenEntity.getId(), tokenEntity.getToken(), user, tokenEntity.getExpiryDate());
    }

    public User convertUser(UserEntity userEntity) {
        return new User(userEntity.getName(), userEntity.getEmail(), userEntity.getPasswordHash(), userEntity.getPasswordSalt(), userEntity.isEnabled(), userEntity.getRolesList());
    }

    public Image convertImage(ImageEntity imageEntity) {
        return new Image(imageEntity.getId(), imageEntity.getDirectory(), imageEntity.getFileName());
    }

    public Post convertPost(PostEntity postEntity) {
        Image image = convertImage(postEntity.getImage());
        return new Post(postEntity.getId(), postEntity.getMessage(), postEntity.getDateTime(), image);
    }

    public Thread convertThread(ThreadEntity threadEntity) {
        Image image = null;
        if(threadEntity.getImage() != null) {
             image = convertImage(threadEntity.getImage());
        }
        List<Post> posts = null;
        if(!CollectionUtils.isEmpty(threadEntity.getPosts())) {
             posts = threadEntity.getPosts().stream().map(this::convertPost).collect(Collectors.toList());
        } else {
            posts = Collections.emptyList();
        }
        return new Thread(threadEntity.getId(), threadEntity.getMessage(), threadEntity.getDateTime(), image, posts);
    }

    public Channel convertChannel(ChannelEntity channelEntity, boolean lazy) {
        if (!lazy) {
            List<Thread> threads = channelEntity.getThreads().stream().map(this::convertThread).collect(Collectors.toList());
            return new Channel(channelEntity.getId(), channelEntity.getName(), threads);
        } else {
            return new Channel(channelEntity.getId(), channelEntity.getName(), null);
        }
    }
}
