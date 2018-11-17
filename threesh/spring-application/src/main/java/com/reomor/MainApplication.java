package com.reomor;

import com.reomor.core.domain.UserRoles;
import com.reomor.impl.service.UserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.reomor")
public class MainApplication implements CommandLineRunner {

    @Autowired
    private UserAuthorizationService userService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.register("user", "user@user.com", "123", UserRoles.ROLE_ADMIN);
    }
}
