package com.reomor.controller.mvc;

import com.reomor.core.domain.Image;
import com.reomor.core.domain.Thread;
import com.reomor.core.service.ImageService;
import com.reomor.core.service.ThreadService;
import com.reomor.dto.PostDto;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
            @RequestParam MultipartFile file
    ) {
        if (postDto.getThreadId() != null) { // add post to existing thread
            Long storedImageId = imageService.storeAndSave(postDto.threadIdAsString(), file).getId();
            threadService.createPost(postDto.getThreadId(), storedImageId, postDto.toPost());
        } else { // add new thread
            Image image = new Image(null, "", StringUtils.cleanPath(file.getOriginalFilename()));
            Thread thread = threadService.create(channelId, postDto.toThread(image));
            imageService.store(thread.getImage().getDirectory(), file);
        }
        return "redirect:/channel?id=" + channelId;
    }
}
