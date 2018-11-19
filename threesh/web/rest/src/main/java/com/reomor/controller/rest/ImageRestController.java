package com.reomor.controller.rest;

import com.reomor.core.domain.Post;
import com.reomor.core.service.ImageService;
import com.reomor.core.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class ImageRestController {

    private final ImageService imageService;
    private final ThreadService threadService;

    @Autowired
    public ImageRestController(ImageService imageService, ThreadService threadService) {
        this.imageService = imageService;
        this.threadService = threadService;
    }

    @PostMapping("/rest/image/{postId}")
    public Post postThreadReply(
            @PathVariable("postId") Integer postId,
            @RequestParam("file") MultipartFile file
            ) {
        Post post = new Post();
        return post;
    }
}
