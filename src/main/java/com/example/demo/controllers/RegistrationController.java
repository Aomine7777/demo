package com.example.demo.controllers;


import com.example.demo.models.MyUser;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new MyUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") MyUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userService.loadUserByUserName(user.getUserName()).isPresent()) {
            model.addAttribute("errorMessage", "User with this username already exists");
            return "register";
        }

        userService.saveUser(user);

        return "redirect:/login?success";
    }
}