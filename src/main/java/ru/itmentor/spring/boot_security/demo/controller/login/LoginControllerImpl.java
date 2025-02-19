package ru.itmentor.spring.boot_security.demo.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControllerImpl implements LoginController {
    @Override
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
