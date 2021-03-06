
package com.blogmeproject2.controllers;

import com.blogmeproject2.models.User;
import com.blogmeproject2.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AuthenticationController {
    private UsersRepository repository;
    private PasswordEncoder encoder;

    @Autowired
    public AuthenticationController(UsersRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/users/create")
    public String registerUser(
        @Valid User user,
        Errors validation,
        Model viewModel,
        @RequestParam(name = "password_confirm") String passwordConfirmation
    ) {
        if (!passwordConfirmation.equals(user.getPassword())) {
            validation.rejectValue(
                "password",
                "user.password",
                "Your passwords do not match"
            );
        }
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return "users/register";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);

        return "redirect:/login";
    }
}
