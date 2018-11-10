package com.reomor.core.service;

import com.reomor.core.domain.Post;
import com.reomor.core.domain.PostThread;
import com.reomor.core.domain.ThreadChannel;

import java.util.List;

public interface PostThreadService {

    PostThread create(PostThread postThread);

    PostThread get(Long id);

    PostThread delete(PostThread postThread);

    PostThread update(PostThread postThread);

    List<Post> getAllPostsInThread(Long threadId);

    Post createPost(Post post);

    Post getPost(Long id);

    Post deletePost(Post post);

    Post updatePost(Post post);
}
