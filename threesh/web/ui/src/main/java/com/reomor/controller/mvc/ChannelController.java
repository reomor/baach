package com.reomor.controller.mvc;

import com.reomor.core.service.ChannelService;
import com.reomor.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ChannelController {

    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping("/channel")
    public String listPage(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("channel", channelService.getWithThreads(id));
        }
        model.addAttribute("channels", channelService.getAll());
        model.addAttribute("postDto", new PostDto());
        return "channel/list";
    }
}
