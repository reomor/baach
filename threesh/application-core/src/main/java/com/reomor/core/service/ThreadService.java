package com.reomor.core.service;

import com.reomor.core.domain.Post;
import com.reomor.core.domain.Thread;

import java.util.List;

public interface ThreadService {

    Thread create(Thread thread);

    Thread get(Long id);

    Thread delete(Thread thread);

    Thread update(Thread thread);

    List<Post> getAllPostsInThread(Long threadId);

    Post createPost(Post post);

    Post getPost(Long id);

    Post deletePost(Post post);

    Post updatePost(Post post);
}
