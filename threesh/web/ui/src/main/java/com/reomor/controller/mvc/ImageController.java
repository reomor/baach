package com.reomor.controller.mvc;

import com.reomor.core.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/image")
    public String users(Model model) {
        model.addAttribute("images", imageService.loadAll());
        return "image/list";
    }
}
