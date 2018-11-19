package com.reomor.controller.mvc;

import com.reomor.core.service.ChannelService;
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
    public String listPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("channels", channelService.getAll());
        model.addAttribute("channel", channelService.getWithThreads(id));
        return "channel/list";
    }
}
