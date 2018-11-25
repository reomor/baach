package com.reomor.controller.mvc;

import com.reomor.core.domain.User;
import com.reomor.core.domain.VerificationToken;
import com.reomor.dto.RegistrationFormDto;
import com.reomor.impl.service.UserService;
import com.reomor.registration.OnRegistrationCompleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@Slf4j
@Controller
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public RegistrationController(UserService userService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/registration")
    public String registerForm(Model model) {
        log.info("Register page");
        model.addAttribute("registrationForm", new RegistrationFormDto());
        return "registration/form";
    }

    @PostMapping("/registration")
    public String register(
            @Valid @ModelAttribute("registrationForm") RegistrationFormDto registrationFormDto,
            Errors errors,
            Model model,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        log.info("Attempt to register with " + registrationFormDto);
        if (errors.hasErrors()) {
            log.info("Errors during registration");
            model.addAttribute("registrationForm", registrationFormDto);
            return "registration/form";
        }
        // is validated
        User userRegistered = userService.register(registrationFormDto.getName(), registrationFormDto.getEmail(), registrationFormDto.getPassword());
        if (userRegistered == null) {
            redirectAttributes.addFlashAttribute("alertFail", "Failed to register. :(");
            return "redirect:/login";
        }
        try {
            String appUrl = request.getRequestURL().toString();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userRegistered, request.getLocale(), appUrl));
        } catch (Exception me) {
            redirectAttributes.addFlashAttribute("alertFail", "Failed to register. :(");
            return "redirect:/login";
        }
        log.info("Successfully registered");
        redirectAttributes.addFlashAttribute("alertSuccess", "Successfully registered. Please, confirm registration by link in email :)");
        return "redirect:/login";
    }

    @GetMapping(value = "/registrationConfirm")
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            //String message = messages.getMessage("auth.message.invalidToken", null, locale);
            String message = "Invalid token";
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        if (verificationToken.isExpired()) {
            //String messageValue = messages.getMessage("auth.message.expired", null, locale);
            String message = "Expired token";
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);
        userService.update(user);
        return "redirect:/login";
    }
}
