package com.reomor.controller.rest;

import com.reomor.core.domain.Post;
import com.reomor.core.service.ImageService;
import com.reomor.core.service.ThreadService;
import com.reomor.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ThreadRestController {

    private final ImageService imageService;
    private final ThreadService threadService;

    public ThreadRestController(ImageService imageService, ThreadService threadService) {
        this.imageService = imageService;
        this.threadService = threadService;
    }

    @PostMapping("/rest/thread/post/{postId}")
    public Post postThreadReply(
            @PathVariable("postId") Integer postId,
            @RequestBody PostDto postDto
            ) {
        Post post = new Post();
        return post;
    }
}
