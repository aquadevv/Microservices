package ru.itmentor.crud.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmentor.crud.dto.UserDto;

public interface UserController {
    String getAllUsers(Model model);

    String addUser(@ModelAttribute("userDto") UserDto userDto);

    String deleteUser(@RequestParam("id") Long userId);

    String updateUser(@RequestParam("id") Long userId, @ModelAttribute("user") UserDto userDto);
}