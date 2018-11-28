package com.reomor.controller.mvc;

import com.reomor.core.service.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
public class IndexPageController {

    private final ChannelService channelService;

    @Autowired
    public IndexPageController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        log.info("Main page");
        model.addAttribute("channels", channelService.getAll());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost() {
        return "redirect:/login";
    }
}