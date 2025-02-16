package ru.itmentor.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.crud.dto.UserDto;
import ru.itmentor.crud.service.UserService;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private static final String REDIRECT_URL = "redirect:/users";

    @GetMapping
    @Override
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userDto", new UserDto());
        return "users/list";
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
