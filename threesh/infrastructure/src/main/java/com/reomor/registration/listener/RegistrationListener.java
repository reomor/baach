package com.reomor.registration.listener;


import java.util.UUID;

import com.reomor.core.domain.User;
import com.reomor.impl.service.UserService;
import com.reomor.registration.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final UserService userService;
    private final JavaMailSender mailSender;
    private final Environment env;

    @Autowired
    public RegistrationListener(UserService userService, JavaMailSender mailSender, Environment env) {
        this.userService = userService;
        this.mailSender = mailSender;
        this.env = env;
    }

    // API
    @Override
    @Async
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }

    private SimpleMailMessage constructEmailMessage(
            final OnRegistrationCompleteEvent event,
            final User user,
            final String token
    ) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "?token=" + token;
        final String message = "To confirm registration go to link:";
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
