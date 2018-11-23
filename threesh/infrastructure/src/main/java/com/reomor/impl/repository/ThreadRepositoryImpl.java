package com.reomor.impl.repository;

import com.reomor.core.domain.Post;
import com.reomor.core.domain.Thread;
import com.reomor.core.repository.ThreadRepository;
import com.reomor.impl.entity.ImageEntity;
import com.reomor.impl.entity.PostEntity;
import com.reomor.impl.entity.ThreadEntity;
import com.reomor.impl.mapper.DomainToEntityMapper;
import com.reomor.impl.mapper.EntityToDomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ThreadRepositoryImpl implements ThreadRepository {

    private final JpaThreadRepository threadRepository;
    private final JpaPostRepository postRepository;
    private final JpaImageRepository imageRepository;
    private final JpaChannelRepository channelRepository;
    private final DomainToEntityMapper domainToEntityMapper;
    private final EntityToDomainMapper entityToDomainMapper;

    @Autowired
    public ThreadRepositoryImpl(
            JpaThreadRepository threadRepository,
            JpaPostRepository postRepository,
            JpaImageRepository imageRepository,
            JpaChannelRepository channelRepository,
            DomainToEntityMapper domainToEntityMapper,
            EntityToDomainMapper entityToDomainMapper
    ) {
        this.threadRepository = threadRepository;
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
        this.channelRepository = channelRepository;
        this.domainToEntityMapper = domainToEntityMapper;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public Thread create(Long channelId, Thread thread) {
        ThreadEntity threadEntity = domainToEntityMapper.convertThread(thread);
        threadEntity.setChannel(channelRepository.getOne(channelId));
        ThreadEntity savedEntity = threadRepository.save(threadEntity);
        ImageEntity savedEntityImage = savedEntity.getImage();
        savedEntityImage.setDirectory(String.valueOf(threadEntity.getId()));
        imageRepository.save(savedEntityImage);
        return entityToDomainMapper.convertThread(threadEntity);
    }

    @Override
    public Thread get(Long id) {
        return entityToDomainMapper.convertThread(threadRepository.findById(id).orElse(null));
    }

    @Override
    public void delete(Thread thread) {
        threadRepository.deleteById(thread.getId());
    }

    @Override
    public Thread update(Long channelId, Thread thread) {
        return create(channelId, thread);
    }

    @Override
    public List<Thread> findAllThreadsInChannel(Long channelId) {
        return threadRepository.findAllByChannel_Id(channelId).stream().map(entityToDomainMapper::convertThread).collect(Collectors.toList());
    }

    @Override
    public Post createPost(Long threadId, Long imageId, Post post) {
        ThreadEntity threadEntity = threadRepository.getOne(threadId);
        ImageEntity imageEntity = imageRepository.getOne(imageId);
        PostEntity postEntity = domainToEntityMapper.convertPost(post);
        postEntity.setThread(threadEntity);
        postEntity.setImage(imageEntity);
        return entityToDomainMapper.convertPost(postRepository.save(postEntity));
    }

    @Override
    public Post getPost(Long id) {
        return entityToDomainMapper.convertPost(postRepository.findById(id).orElse(null));
    }

    @Override
    public void deletePost(Post post) {
        postRepository.deleteById(post.getId());
    }

    @Override
    public Post updatePost(Long threadId, Long imageId, Post post) {
        return createPost(threadId, imageId, post);
    }

    @Override
    public List<Post> getAllPostsInThread(Long threadId) {
        return postRepository.findAllByThread_Id(threadId).stream().map(entityToDomainMapper::convertPost).collect(Collectors.toList());
    }
}
