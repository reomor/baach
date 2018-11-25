package com.reomor.controller.mvc;

import com.reomor.core.domain.UserRoles;
import com.reomor.dto.UserFormDto;
import com.reomor.impl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String users(Model model) {
        model.addAttribute("userForm", new UserFormDto());
        model.addAttribute("users", userService.getAll());
        return "user/list";
    }

    @PostMapping("/user")
    public String addUser(
            @Valid @ModelAttribute("userForm") UserFormDto userFormDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("alertFail", "Failed validation");
            return "redirect:/user";
        }
        userService.register(userFormDto.getName(), userFormDto.getEmail(), userFormDto.getPassword(), true, UserRoles.ROLE_USER);
        return "redirect:/user";
    }
}
