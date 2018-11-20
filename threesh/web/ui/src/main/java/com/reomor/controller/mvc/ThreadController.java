package com.reomor.controller.mvc;

import com.reomor.core.domain.Image;
import com.reomor.core.domain.Post;
import com.reomor.core.service.ImageService;
import com.reomor.core.service.ThreadService;
import com.reomor.dto.PostDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ThreadController {

    private final ImageService imageService;
    private final ThreadService threadService;

    public ThreadController(ImageService imageService, ThreadService threadService) {
        this.imageService = imageService;
        this.threadService = threadService;
    }

    @PostMapping("/channel/thread")
    public String addPostInThread(
            @RequestParam("channel") Long channelId,
            @ModelAttribute PostDto postDto,
            @RequestParam(required = false) MultipartFile file
    ) {
        Image storedImage = imageService.store(postDto.threadIdAsString(), file);
        Post post = postDto.toPost();
        threadService.createPost(postDto.getThreadId(), storedImage.getId(), post);
        return "redirect:/channel?id=" + channelId;
    }
}
