package com.reomor.core.repository;

import com.reomor.core.domain.Post;
import com.reomor.core.domain.PostThread;

import java.util.List;

public interface PostThreadRepository {

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
