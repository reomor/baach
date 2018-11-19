package com.reomor.controller.mvc;

import com.reomor.core.service.ImageService;
import com.reomor.core.service.ThreadService;
import org.springframework.stereotype.Controller;

@Controller
public class ThreadController {

    private final ImageService imageService;
    private final ThreadService threadService;

    public ThreadController(ImageService imageService, ThreadService threadService) {
        this.imageService = imageService;
        this.threadService = threadService;
    }
}
