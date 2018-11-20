package com.reomor.impl.service;

import com.reomor.core.domain.Post;
import com.reomor.core.domain.Thread;
import com.reomor.core.service.ThreadService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadServiceImpl implements ThreadService {

    @Override
    public Thread create(Thread thread) {
        return null;
    }

    @Override
    public Thread get(Long id) {
        return null;
    }

    @Override
    public void delete(Thread thread) {

    }

    @Override
    public Thread update(Thread thread) {
        return null;
    }

    @Override
    public List<Thread> findAllThreadsInChannel(Long channelId) {
        return null;
    }

    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public Post getPost(Long id) {
        return null;
    }

    @Override
    public void deletePost(Post post) {

    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public List<Post> getAllPostsInThread(Long threadId) {
        return null;
    }
}
