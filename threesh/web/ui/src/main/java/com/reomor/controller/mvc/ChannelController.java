package com.reomor.controller.mvc;

import com.reomor.core.domain.Channel;
import com.reomor.core.service.ChannelService;
import com.reomor.dto.ChannelFormDto;
import com.reomor.dto.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

        if (id == null) {
            model.addAttribute("channels", channelService.getAll());
            model.addAttribute("channelForm", new ChannelFormDto());
            return "channel/list";
        }

        model.addAttribute("channel", channelService.getWithThreads(id));
        model.addAttribute("channels", channelService.getAll());
        model.addAttribute("postDto", new PostDto());
        return "channel/view";
    }

    @PostMapping("/channel")
    public String createChannel(
            @ModelAttribute("channelForm") ChannelFormDto channelFormDto,
            HttpServletRequest request
    ) {
        channelService.create(channelFormDto.toChannel());
        String referrer = request.getHeader("Referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/channel/delete")
    public String deleteChannel(@RequestParam(value = "id") Long id) {
        Channel channel = channelService.get(id);
        if (channel != null) {
            channelService.delete(channel);
        }
        return "redirect:/channel";
    }
}
