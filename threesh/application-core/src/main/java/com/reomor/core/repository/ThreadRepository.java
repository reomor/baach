package com.reomor.core.repository;

import com.reomor.core.domain.Post;
import com.reomor.core.domain.Thread;

import java.util.List;

public interface ThreadRepository {

    Thread create(Thread thread);

    Thread get(Long id);

    void delete(Thread thread);

    Thread update(Thread thread);

    List<Thread> findAllThreadsInChannel(Long channelId);

    Post createPost(Post post);

    Post getPost(Long id);

    void deletePost(Post post);

    Post updatePost(Post post);

    List<Post> getAllPostsInThread(Long threadId);
}
