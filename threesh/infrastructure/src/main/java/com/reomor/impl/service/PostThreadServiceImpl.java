package com.reomor.impl.service;

import com.reomor.core.domain.Post;
import com.reomor.core.domain.PostThread;
import com.reomor.core.repository.ImageStorage;
import com.reomor.core.service.PostThreadService;

import java.util.List;


public class PostThreadServiceImpl implements PostThreadService {

    private final ImageStorage imageStorage;

    public PostThreadServiceImpl(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }

    @Override
    public PostThread create(PostThread postThread) {
        return null;
    }

    @Override
    public PostThread get(Long id) {
        return null;
    }

    @Override
    public PostThread delete(PostThread postThread) {
        return null;
    }

    @Override
    public PostThread update(PostThread postThread) {
        return null;
    }

    @Override
    public List<Post> getAllPostsInThread(Long threadId) {
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
    public Post deletePost(Post post) {
        return null;
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }
}
