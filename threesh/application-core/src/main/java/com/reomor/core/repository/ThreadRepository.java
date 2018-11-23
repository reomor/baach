package com.reomor.core.repository;

import com.reomor.core.domain.Post;
import com.reomor.core.domain.Thread;

import java.util.List;

public interface ThreadRepository {

    Thread create(Long channelId, Thread thread);

    Thread get(Long id);

    void delete(Thread thread);

    Thread update(Long channelId, Thread thread);

    List<Thread> findAllThreadsInChannel(Long channelId);

    Post createPost(Long threadId, Long imageId, Post post);

    Post getPost(Long id);

    void deletePost(Post post);

    Post updatePost(Long threadId, Long imageId, Post post);

    List<Post> getAllPostsInThread(Long threadId);
}
