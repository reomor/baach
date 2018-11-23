package com.reomor.controller.mvc;

import com.reomor.core.service.ChannelService;
import com.reomor.dto.RegistrationFormDto;
import com.reomor.impl.service.UserAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Slf4j
@Controller
public class IndexPageController {

    private final ChannelService channelService;
    private final UserAuthorizationService userService;

    @Autowired
    public IndexPageController(
            ChannelService channelService,
            UserAuthorizationService userService) {
        this.channelService = channelService;
        this.userService = userService;
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

    @GetMapping("/register")
    public String registerForm(Model model) {
        log.info("Register page");
        model.addAttribute("registrationForm", new RegistrationFormDto());
        return "registration/form";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("registrationForm") RegistrationFormDto registrationFormDto,
            Errors errors,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        log.info("Attempt to register with " + registrationFormDto);
        if (errors.hasErrors()) {
            log.info("Errors during registration");
            model.addAttribute("registrationForm", registrationFormDto);
            return "registration/form";
        }
        log.info("Successfully registered");
        // is validated
        userService.register(registrationFormDto.getName(), registrationFormDto.getEmail(), registrationFormDto.getPassword());

        redirectAttributes.addFlashAttribute("alertSuccess", "Successfully registered. Please, login :)");
        return "redirect:/login";
    }
}