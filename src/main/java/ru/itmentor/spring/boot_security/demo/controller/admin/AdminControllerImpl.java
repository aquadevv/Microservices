package ru.itmentor.spring.boot_security.demo.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final UserService userService;

    private static final String REDIRECT_URL = "redirect:/admin";

    @GetMapping
    @Override
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userDto", new UserDto());
        return "admin";
    }

    @PostMapping
    @Override
    public String addUser(@ModelAttribute("userDto") UserDto userDto) {
        userService.createUser(userDto);
        return REDIRECT_URL;
    }

    @DeleteMapping
    @Override
    public String deleteUser(@RequestParam("id") Long userId) {
        userService.deleteUser(userId);
        return REDIRECT_URL;
    }

    @PutMapping
    @Override
    public String updateUser(@RequestParam("id") Long userId, @ModelAttribute("user") UserDto userDto) {
        userService.updateUser(userId, userDto);
        return REDIRECT_URL;
    }
}
