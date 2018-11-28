package com.reomor.controller.mvc;

import com.reomor.core.domain.User;
import com.reomor.core.domain.VerificationToken;
import com.reomor.dto.RegistrationFormDto;
import com.reomor.exception.UserAlreadyRegesteredException;
import com.reomor.impl.repository.TokenRepository;
import com.reomor.impl.service.UserService;
import com.reomor.registration.OnRegistrationCompleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
public class RegistrationController {

    private static final String FAILED_TO_REGISTER = "Failed to register. :(";
    private static final String SUCCESSFULLY_REGISTERED = "Successfully registered. Please, confirm registration by link in email :)";
    private static final String INVALID_TOKEN = "Invalid token";
    private static final String TOKEN_IS_EXPIRED = "Token is expired. Send new one - check email.";
    private static final String CREDENTIALS_ALREADY_EXISTS = "User with this credentials already exists";

    private static final String REGISTRATION_CONFIRM_MAP = "registrationConfirm";
    private static final String CONFIRMED_PLEASE_LOGIN = "User confirmed. Please, login :)";

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final TokenRepository tokenRepository;

    @Autowired
    public RegistrationController(
            UserService userService,
            ApplicationEventPublisher eventPublisher,
            TokenRepository tokenRepository
    ) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/registration")
    public String registerForm(Model model) {
        log.info("New user registration");
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
        User userRegistered = null;
        try {
            userRegistered = userService.register(registrationFormDto.getName(), registrationFormDto.getEmail(), registrationFormDto.getPassword());
        } catch (UserAlreadyRegesteredException e) {
            log.info("User with this credentials already exists");
            redirectAttributes.addFlashAttribute("alertFail", CREDENTIALS_ALREADY_EXISTS);
            return "redirect:/login";
        }
        if (userRegistered == null) {
            log.info("User with this credentials not found");
            redirectAttributes.addFlashAttribute("alertFail", FAILED_TO_REGISTER);
            return "redirect:/login";
        }
        try {
            log.info("Sending email with confirmation link");
            confirmRegistrationByEmail(userRegistered, request);
        } catch (Exception me) {
            log.info("Failed to send confirmation email");
            redirectAttributes.addFlashAttribute("alertFail", FAILED_TO_REGISTER);
            return "redirect:/login";
        }
        log.info("Successfully registered");
        redirectAttributes.addFlashAttribute("alertSuccess", SUCCESSFULLY_REGISTERED);
        return "redirect:/login";
    }

    @GetMapping(value = "/registrationConfirm")
    public String confirmRegistration(
            @RequestParam("token") String token,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        log.info("Getting token from db");
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            log.info("Token not found");
            redirectAttributes.addFlashAttribute("alertFail", INVALID_TOKEN);
            return "redirect:/login";
        }
        log.info("Token found: check expiration status");
        User user = verificationToken.getUser();
        if (verificationToken.isExpired()) {
            log.info("Token is expired: ", token);
            redirectAttributes.addFlashAttribute("alertFail", TOKEN_IS_EXPIRED);
            tokenRepository.delete(verificationToken.getId());
            confirmRegistrationByEmail(user, request);
            return "redirect:/login";
        }
        log.info("User activated");
        user.setEnabled(true);
        userService.update(user);
        redirectAttributes.addFlashAttribute("alertSuccess", CONFIRMED_PLEASE_LOGIN);
        return "redirect:/login";
    }

    private void confirmRegistrationByEmail(User userRegistered, HttpServletRequest request) {
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userRegistered, getUri(request, REGISTRATION_CONFIRM_MAP)));
    }

    private String getUri(HttpServletRequest request, String mapping) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + mapping;
    }
}
