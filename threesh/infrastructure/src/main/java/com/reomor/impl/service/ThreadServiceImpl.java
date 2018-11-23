package com.reomor.impl.service;

import com.reomor.core.domain.Post;
import com.reomor.core.domain.Thread;
import com.reomor.core.repository.ThreadRepository;
import com.reomor.core.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;

    @Autowired
    public ThreadServiceImpl(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @Override
    public Thread create(Long channelId, Thread thread) {
        return threadRepository.create(channelId, thread);
    }

    @Override
    public Thread get(Long id) {
        return null;
    }

    @Override
    public void delete(Thread thread) {

    }

    @Override
    public Thread update(Long channelId, Thread thread) {
        return null;
    }

    @Override
    public List<Thread> findAllThreadsInChannel(Long channelId) {
        return null;
    }

    @Override
    public Post createPost(Long threadId, Long imageId, Post post) {
        return threadRepository.createPost(threadId, imageId, post);
    }

    @Override
    public Post getPost(Long id) {
        return null;
    }

    @Override
    public void deletePost(Post post) {

    }

    @Override
    public Post updatePost(Long threadId, Long imageId, Post post) {
        return null;
    }

    @Override
    public List<Post> getAllPostsInThread(Long threadId) {
        return null;
    }
}
